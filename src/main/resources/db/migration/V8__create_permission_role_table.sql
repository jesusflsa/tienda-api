CREATE TABLE IF NOT EXISTS role_permissions (
    role_id       BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, permission_id)
);

INSERT INTO role_permissions VALUES
# Admin Permissions
(1, 1),
(1, 2),
(1, 3),
(1, 4),
# Worker Permission
(2, 1),
(2, 2),
(2, 3),
# Developer Permissions
(3, 1),
(3, 2),
(3, 3),
(3, 4),
# Client Permissions
(4, 2);