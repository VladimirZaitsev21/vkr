package ru.zvo.walkingroutesgh.adminpanel.command;

import ru.zvo.walkingroutesgh.adminpanel.command.admin.AddUserPageCommand;
import ru.zvo.walkingroutesgh.adminpanel.command.admin.AdminMainPageCommand;
import ru.zvo.walkingroutesgh.adminpanel.command.admin.DeleteUserCommand;
import ru.zvo.walkingroutesgh.adminpanel.command.admin.SaveUserCommand;
import ru.zvo.walkingroutesgh.adminpanel.command.historian.EditSightCommand;
import ru.zvo.walkingroutesgh.adminpanel.command.historian.EditSightPageCommand;
import ru.zvo.walkingroutesgh.adminpanel.command.historian.HistorianMainPageCommand;
import ru.zvo.walkingroutesgh.adminpanel.command.historian.MyEditsCommand;
import ru.zvo.walkingroutesgh.adminpanel.command.moderator.*;
import ru.zvo.walkingroutesgh.dto.Role;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum CommandType {

    LOGIN {
        @Override
        public Command getCommand() {
            return new LoginCommand();
        }
    },

    EDIT_SIGHT(Role.HISTORIAN) {
        @Override
        public Command getCommand() {
            return new EditSightCommand();
        }
    },

    MY_EDITS {
        @Override
        public Command getCommand() {
            return new MyEditsCommand();
        }
    },

    DELETE_EDIT(Role.HISTORIAN, Role.MODERATOR) {
        @Override
        public Command getCommand() {
            return new DeleteEditCommand();
        }
    },

    DELETE_USER(Role.ADMIN) {
        @Override
        public Command getCommand() {
            return new DeleteUserCommand();
        }
    },

    SAVE_USER(Role.ADMIN) {
        @Override
        public Command getCommand() {
            return new SaveUserCommand();
        }
    },

    USERS_PAGE {
        @Override
        public Command getCommand() {
            return new UsersPageCommand();
        }
    },

    BLOCK_USER(Role.MODERATOR) {
        @Override
        public Command getCommand() {
            return new BlockUserCommand();
        }
    },

    UNBLOCK_USER(Role.MODERATOR) {
        @Override
        public Command getCommand() {
            return new UnblockUserCommand();
        }
    },

    HISTORIAN_EDITS(Role.MODERATOR) {
        @Override
        public Command getCommand() {
            return new HistorianEditsCommand();
        }
    },

    SAVE_EDIT(Role.MODERATOR) {
        @Override
        public Command getCommand() {
            return new SaveEditCommand();
        }
    },

    LOGIN_PAGE {
        @Override
        public Command getCommand() {
            return new LoginPageCommand();
        }
    },

    HISTORIAN_MAIN_PAGE(Role.HISTORIAN) {
        @Override
        public Command getCommand() {
            return new HistorianMainPageCommand();
        }
    },

    MODERATOR_MAIN_PAGE(Role.MODERATOR) {
        @Override
        public Command getCommand() {
            return new ModeratorMainPageCommand();
        }
    },

    ADMIN_MAIN_PAGE(Role.ADMIN) {
        @Override
        public Command getCommand() {
            return new AdminMainPageCommand();
        }
    },

    ADD_USER_PAGE(Role.ADMIN) {
        @Override
        public Command getCommand() {
            return new AddUserPageCommand();
        }
    },

    EDIT_SIGHTS_PAGE(Role.HISTORIAN) {
        @Override
        public Command getCommand() {
            return new EditSightPageCommand();
        }
    },

    LOGOUT {
        @Override
        public Command getCommand() {
            return null;
        }
    };

    public Set<Role> getRoles() {
        return roles;
    }

    private final Set<Role> roles = new HashSet<>();

    CommandType(Role... roles) {
        Collections.addAll(this.roles, roles);
    }

    public static CommandType getTypeByName(String commandType) {
        try {
            return CommandType.valueOf(commandType.toUpperCase());
        } catch (Exception e) {
            throw new RuntimeException("Undefined command name: " + commandType);
        }
    }

    public boolean canRoleInvokeCommand(Role role) {
        return roles.size() == 0 || roles.contains(role);
    }

    public abstract Command getCommand();

}
