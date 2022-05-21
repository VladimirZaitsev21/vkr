var RouteControl = function (options) {
    this._element;
    this._startAddressInput;
    this._finishAddressInput;
    this._markOnMap;
    this._clearButton;
    this._totalInfoDiv;
    this._buildRouteButton;
    this._showSightsButton;
    this._secondBar;
    this._routeLength;
    this._routeTime;

    this._map = options.map;
    this._source;

    this._numPoint;
    this._startPoint;
    this._finishPoint;
    this._route;

    this._popup;

    this._isMarkingNow = false;

    this._init();

    ol.control.Control.call(this, {
        element: this._element,
        target: options.target
    });
};

var ol_ext_inherits = function (child, parent) {
    child.prototype = Object.create(parent.prototype);
    child.prototype.constructor = child;
};

ol_ext_inherits(RouteControl, ol.control.Control);

RouteControl.prototype._init = function () {
    this._element = document.getElementById('routeControl');

    // this._startAddressInput = document.getElementById( 'startAddress' );
    // this._finishAddressInput = document.getElementById( 'finishAddress' );
    this._startAddressInput = document.getElementById('fromInput');
    this._finishAddressInput = document.getElementById('toInput');
    this._totalInfoDiv = document.getElementById('totalInfo');
    this._clearButton = document.getElementById('clear');
    this._buildRouteButton = document.getElementById('buildRoute');
    this._showSightsButton = document.getElementById('showSights');
    this._secondBar = document.getElementById("second-bar");
    this._routeLength = document.getElementById("length");
    this._routeTime = document.getElementById("time");
    this._markOnMap = document.getElementById("markOnMap");

    // при клике на поле ввода начального адреса на карте будет устанавливаться
    // точка отправления, при клике на поле конечного адреса - точка прибытия
    this._startAddressInput.addEventListener('click', this._setRoutePoint.bind(this, 0), false);
    this._startAddressInput.addEventListener('touchstart', this._setRoutePoint.bind(this, 0), false);

    this._finishAddressInput.addEventListener('click', this._setRoutePoint.bind(this, 1), false);
    this._finishAddressInput.addEventListener('touchstart', this._setRoutePoint.bind(this, 1), false);

    this._clearButton.addEventListener('click', this._clear.bind(this, 1), false);
    this._clearButton.addEventListener('touchstart', this._clear.bind(this, 1), false);

    this._buildRouteButton.addEventListener('click', this._buildRoute.bind(this, 1), false);
    this._buildRouteButton.addEventListener('touchstart', this._buildRoute.bind(this, 1), false);
    this._showSightsButton.addEventListener('click', this._showSights.bind(this, 1), false);
    this._showSightsButton.addEventListener('touchstart', this._showSights.bind(this, 1), false);

    this._markOnMap.addEventListener('click', this._markNow.bind(this), false);
    // подписываемся на событие клика на карте для установки маркеров
    this._map.on('click', this._processClick.bind(this));


    var styleFunc = function (feature, resolution) {
        var marker = feature.get('marker');
        var featureName = feature.get('name');

        if (marker) {
            // marker определён - возвращаем стили для точки
            return [new ol.style.Style({
                image: new ol.style.Icon({
                    anchor: [0.5, 1],
                    src: marker
                })
            })];
        } else if (featureName !== 'sightCircle') {
            // marker не определён - возвращаем стили для линии
            return [new ol.style.Style({
                stroke: new ol.style.Stroke({
                    color: 'blue',
                    width: 3
                })
            })];
        } else {
            return [new ol.style.Style({
                stroke: new ol.style.Stroke({
                    color: 'green',
                    width: 2
                })
            })]
        }
    };

    this._source = new ol.source.Vector();
    var markerLayer = new ol.layer.Vector({
        source: this._source,
        style: styleFunc
    });
    this._map.addLayer(markerLayer);

    this._popup = new Popup();
    map.addOverlay(this._popup);
};

RouteControl.prototype._clear = function () {
    this._route = null;
    this._numPoint = null;
    this._startPoint = null;
    this._finishPoint = null;
    this._source.clear();

    this._startAddressInput.value = "";
    this._finishAddressInput.value = "";

    this._totalInfoDiv.style.display = "none"
    this._totalInfoDiv.innerHTML = "";

    this._secondBar.style.width = '0';
};

RouteControl.prototype._setRoutePoint = function (numPoint) {
    this._numPoint = numPoint;
};

RouteControl.prototype._createPoint = function (coords, pathToMarker) {
    return new ol.Feature({
        marker: pathToMarker,
        geometry: new ol.geom.Point(coords)
    });
};

RouteControl.prototype._markNow = function () {
    this._isMarkingNow = true;
};

RouteControl.prototype._fillAddress = function (coords, input) {
    var coordsWgs = ol.proj.transform(coords, 'EPSG:3857', 'EPSG:4326');
    console.log(coordsWgs[0]);
    console.log(coordsWgs[1]);
    var url = 'https://nominatim.openstreetmap.org/reverse?format=json&lon='
        + coordsWgs[0] + '&lat=' + coordsWgs[1];
    var request = new XMLHttpRequest();
    request.open("GET", url);
    request.onreadystatechange = function () {
        if (request.readyState == 4) {
            var address = JSON.parse(request.responseText);
            input.value = address.display_name;
        }
    }
    request.send();
};

RouteControl.prototype._parseRoute = function (routesInfo) {
    var points = routesInfo.pointsAsArray;
    points.map(function (l) {
        return l.reverse();
    });

    var polyline = new ol.geom.LineString(points);
    polyline.transform('EPSG:4326', 'EPSG:3857');


    var olFeature = new ol.Feature(polyline);
    this._source.addFeatures([olFeature]);
    this._route = olFeature;

    var distanceHours = Math.trunc(routesInfo.time / 1000 / 3600);
    var distanceMinutes;
    if (distanceHours === 0) {
        distanceMinutes = Math.trunc(routesInfo.time / 1000 / 60);
    } else {
        distanceMinutes = (routesInfo.time / 1000 - distanceHours * 3600) / 60;
    }

    this._routeLength.innerText = 'Расстояние: ' + (routesInfo.distance / 1000).toFixed(3) + ' км';
    this._routeTime.innerText = 'Время в пути: ' + 'Время в пути: ' + distanceHours + ' ч, ' + Math.round(distanceMinutes) + ' мин';
    this._secondBar.style.width = '20%';
    this._secondBar.style.border = '1 px solid grey';
    // }
};

RouteControl.prototype._buildRoute = function () {
    if (!this._route) {
        // получаем координаты точки отправления и прибытия в WGS84
        var startCoords = this._startPoint.getGeometry().getCoordinates();
        var finishCoords = this._finishPoint.getGeometry().getCoordinates();
        var startWgs = ol.proj.transform(startCoords, 'EPSG:3857', 'EPSG:4326');
        var finishWgs = ol.proj.transform(finishCoords, 'EPSG:3857', 'EPSG:4326');

        var routeUrl = 'http://192.168.0.5:8080/';
        var urlParams = 'fromLat=' + startWgs[1] + '&fromLng=' + startWgs[0] + '&toLat=' + finishWgs[1] + '&toLng=' + finishWgs[0];
        if (document.getElementsByName("routeType")[0].checked) {
            routeUrl += 'standard_path_dto?' + urlParams;
        } else {
            if (document.getElementsByName("routeType")[1].checked) {
                routeUrl += 'sights_path_dto?' + urlParams + '&ratio=' + document.getElementById('interest').value;
            } else if (document.getElementsByName("routeType")[2].checked) {
                routeUrl += 'eco_path_dto?' + urlParams + '&ratio=' + document.getElementById('interest').value;
            }
        }
        var self = this;
        var request = new XMLHttpRequest();
        request.open("GET", routeUrl);
        request.onreadystatechange = function () {
            if (request.readyState == 4) {
                var routes = JSON.parse(request.responseText);
                self._parseRoute(routes);
            }
        }
        request.send();
    }
};

RouteControl.prototype._processClick = function (evt) {
    if (this._isMarkingNow === true) {
        if ((this._numPoint == null || this._numPoint === 0) && !this._startPoint) {
            //https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-blue.png
            //https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-green.png
            this._startPoint = this._createPoint(evt.coordinate, 'static/markerStart.png');
            this._source.addFeatures([this._startPoint]);
            this._fillAddress(evt.coordinate, this._startAddressInput);
        } else if ((this._numPoint == null || this._numPoint === 1) && !this._finishPoint) {
            this._finishPoint = this._createPoint(evt.coordinate, 'static/markerFinish.png');
            this._source.addFeatures([this._finishPoint]);
            this._fillAddress(evt.coordinate, this._finishAddressInput);
            this._isMarkingNow = false;
        }

        this._numPoint = null;
    } else {
        console.log('else');
        var featuresAtPixel = [];
        map.forEachFeatureAtPixel(evt.pixel, function (feature, layer) {
            console.log('foreach feature');
            featuresAtPixel.push(feature);
        });
        for (let i = 0; i < featuresAtPixel.length; i++) {
            if (featuresAtPixel[i].get('name') === 'sightCircle') {
                console.log(featuresAtPixel[i].get('geometry').get('name'));
                this._popup.show(evt.coordinate, '<b>' + featuresAtPixel[i].get('geometry').get('name') + '</b></br>' + featuresAtPixel[i].get('geometry').get('description'));
                break;
            }
        }
    }
};

RouteControl.prototype._showSights = function (evt) {
    var self = this;
    var request = new XMLHttpRequest();
    request.open("GET", 'http://192.168.0.5:8080/sights');
    request.onreadystatechange = function () {
        if (request.readyState == 4) {
            var data = JSON.parse(request.responseText);
            for (let i = 0; i < data.length; i++) {
                data[i].points.map(function (l) {
                    return l.reverse();
                });

                var center = ol.proj.transform(data[i].points[0], 'EPSG:4326', 'EPSG:3857');
                var view = self._map.getView();
                var projection = view.getProjection();
                var resolutionAtEquator = view.getResolution();
                var pointResolution = ol.proj.getPointResolution(projection, resolutionAtEquator, center);
                //projection.getPointResolution(resolutionAtEquator, center);
                var resolutionFactor = resolutionAtEquator / pointResolution;
                var radius = (20 / projection.getMetersPerUnit()) * resolutionFactor;

                var circle = new ol.geom.Circle(center, radius);

                circle.set('name', data[i].name);
                circle.set('description', data[i].description);
                var circleFeature = new ol.Feature({geometry: circle, name: 'sightCircle'});
                self._source.addFeatures([circleFeature]);
            }
        }
    }
    request.send();
};

function showSightScale() {
    let sightScaleLabel = document.getElementById("sightScaleLabel");
    let sightScale = document.getElementById("interest");
    let sightsRadios = document.getElementsByName("routeType");
    // alert(string);
    if (sightsRadios[0].checked) {
        sightScaleLabel.style.display = "none";
        sightScale.style.display = "none";
    } else {
        if (sightsRadios[1].checked) {
            sightScaleLabel.innerText = "Интересность";
            sightScaleLabel.style.display = "block";
            sightScale.style.display = "block";
        } else {
            sightScaleLabel.innerText = "Экологичность";
            sightScaleLabel.style.display = "block";
            sightScale.style.display = "block";
        }
    }

}

var map = new ol.Map({
    target: 'map',
    projection: 'EPSG:4326'
});

var osmLayer = new ol.layer.Tile({
    source: new ol.source.OSM()
});
map.addLayer(osmLayer);

map.addControl(new RouteControl({map: map}));

var centerCoords = ol.proj.transform([39.707637, 54.630183], 'EPSG:4326', 'EPSG:3857');

var view = new ol.View({
    center: centerCoords,
    zoom: 12,
});
map.setView(view);

// var popup = new Popup();
// map.addOverlay(popup);
//
// map.on('singleclick', function (evt) {
//     console.log('single click');
//     // var prettyCoord = ol.coordinate.toStringHDMS(ol.proj.transform(evt.coordinate, 'EPSG:3857', 'EPSG:4326'), 2);
//     // popup.show(evt.coordinate, '<div><h2>Coordinates</h2><p>' + prettyCoord + '</p></div>');
//     var featuresAtPixel = [];
//     map.forEachFeatureAtPixel(evt.pixel, function (feature, layer) {
//         console.log('foreach feature');
//         featuresAtPixel.push(feature);
//     });
//     for (let i = 0; i < featuresAtPixel.length; i++) {
//         if (featuresAtPixel[i].get('name') === 'sightCircle') {
//             console.log(featuresAtPixel[i].get('geometry').get('name'));
//         }
//     }
//
// });
