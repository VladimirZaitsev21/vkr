package ru.zvo.walkingroutesgh.config;

import com.graphhopper.GraphHopper;
import com.graphhopper.GraphHopperConfig;
import com.graphhopper.config.Profile;
import com.graphhopper.reader.postgis.GraphHopperPostgis;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.routing.util.FootFlagEncoder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.zvo.walkingroutesgh.adminpanel.filters.*;
import ru.zvo.walkingroutesgh.adminpanel.listeners.SessionListener;
import ru.zvo.walkingroutesgh.adminpanel.servlets.FrontController;
import ru.zvo.walkingroutesgh.encoders.EcoEncoder;
import ru.zvo.walkingroutesgh.encoders.SightEncoder;
import ru.zvo.walkingroutesgh.hoppers.EcoHopper;
import ru.zvo.walkingroutesgh.hoppers.SightHopper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;

@Configuration
@ComponentScan("ru.zvo")
public class BeanConfiguration {

    private FlagEncoder standardEncoder;
    private FlagEncoder sightsEncoder;
    private FlagEncoder ecoEncoder;

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(
                new FrontController(), "/frontController/*");
        bean.setLoadOnStartup(1);
        return bean;
    }

    @Bean
    public ServletListenerRegistrationBean<SessionListener> servletListenerRegistrationBean() {
        ServletListenerRegistrationBean<SessionListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(new SessionListener());
        return servletListenerRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean commandFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new CommandFilter());
        filterRegistrationBean.addUrlPatterns("/frontController");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean encodingFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new EncodingFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("encoding", "UTF-8");
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }

    @Bean
    public Connection connection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found during driver registering");
            e.printStackTrace();
        }
        String url = "jdbc:postgresql://localhost:5432/osm_db_custom_ryazan_2";
        try {
            connection = DriverManager.getConnection(url, "postgres", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Bean
    public GraphHopper standardHopper() {
        standardEncoder = new FootFlagEncoder();
        GraphHopper graphHopper = new GraphHopperPostgis().forServer();
        GraphHopperConfig graphHopperConfig = new GraphHopperConfig();
        graphHopperConfig.putObject("db.host", "localhost");
        graphHopperConfig.putObject("db.port", "5432");
        graphHopperConfig.putObject("db.database", "osm_db_custom_ryazan_2");
        graphHopperConfig.putObject("db.schema", "public");
        graphHopperConfig.putObject("db.user", "postgres");
        graphHopperConfig.putObject("db.passwd", "password");
        graphHopperConfig.putObject("db.tags_to_copy", "name");

        graphHopperConfig.putObject("datareader.file", "ways");
        graphHopperConfig.putObject("graph.location", "src/main/resources/graphs/gh_standard/");
        graphHopperConfig.putObject("graph.flag_encoders", "foot");

        graphHopperConfig.setProfiles(Collections.singletonList(new Profile("my_foot").setVehicle("foot").setWeighting("fastest")));

        graphHopper.init(graphHopperConfig);
        graphHopper.setEncodingManager(EncodingManager.start().add(standardEncoder).build());
        return graphHopper.importOrLoad();
    }

    @Bean
    public FlagEncoder standardEncoder() {
        return standardEncoder;
    }

    @Bean
    public GraphHopper sightsHopper() {
        sightsEncoder = new SightEncoder();
        GraphHopper graphHopper = new SightHopper().forServer();
        GraphHopperConfig graphHopperConfig = new GraphHopperConfig();
        graphHopperConfig.putObject("db.host", "localhost");
        graphHopperConfig.putObject("db.port", "5432");
        graphHopperConfig.putObject("db.database", "osm_db_custom_ryazan_2");
        graphHopperConfig.putObject("db.schema", "public");
        graphHopperConfig.putObject("db.user", "postgres");
        graphHopperConfig.putObject("db.passwd", "password");
        graphHopperConfig.putObject("db.tags_to_copy", "name");

        graphHopperConfig.putObject("datareader.file", "ways");
        graphHopperConfig.putObject("graph.location", "src/main/resources/graphs/gh_sight/");
        graphHopperConfig.putObject("graph.flag_encoders", "foot");

        graphHopperConfig.setProfiles(Collections.singletonList(new Profile("my_foot").setVehicle("foot").setWeighting("fastest")));

        graphHopper.init(graphHopperConfig);
        graphHopper.setEncodingManager(EncodingManager.start().add(sightsEncoder).build());
        return graphHopper.importOrLoad();
    }

    @Bean
    public FlagEncoder sightsEncoder() {
        return sightsEncoder;
    }

    @Bean
    public GraphHopper ecoHopper() {
        ecoEncoder = new EcoEncoder();
        GraphHopper graphHopper = new EcoHopper().forServer();
        GraphHopperConfig graphHopperConfig = new GraphHopperConfig();
        graphHopperConfig.putObject("db.host", "localhost");
        graphHopperConfig.putObject("db.port", "5432");
        graphHopperConfig.putObject("db.database", "osm_db_custom_ryazan_2");
        graphHopperConfig.putObject("db.schema", "public");
        graphHopperConfig.putObject("db.user", "postgres");
        graphHopperConfig.putObject("db.passwd", "password");
        graphHopperConfig.putObject("db.tags_to_copy", "name");

        graphHopperConfig.putObject("datareader.file", "ways");
        graphHopperConfig.putObject("graph.location", "src/main/resources/graphs/gh_eco/");
        graphHopperConfig.putObject("graph.flag_encoders", "foot");

        graphHopperConfig.setProfiles(Collections.singletonList(new Profile("my_foot").setVehicle("foot").setWeighting("fastest")));

        graphHopper.init(graphHopperConfig);
        graphHopper.setEncodingManager(EncodingManager.start().add(ecoEncoder).build());
        return graphHopper.importOrLoad();
    }

    @Bean
    public FlagEncoder ecoEncoder() {
        return ecoEncoder;
    }

}
