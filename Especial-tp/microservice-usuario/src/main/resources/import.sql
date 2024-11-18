--Insert usuarios
INSERT INTO usuario_db.usuario (nombre, apellido, email, password, telefono, fecha_de_alta, ban) VALUES('Admin', 'admin', 'admin@admin.com', '$2a$12$aJRVvIAm7LJIWpmn/qllROXquaJ0fYbZn/ud2sfwaSPsIifzApVJe', 0, '2024-08-14', FALSE);
INSERT INTO usuario_db.usuario (nombre, apellido, email, password, telefono, fecha_de_alta, ban) VALUES('mantenimiento', 'mantenimiento', 'mantenimiento@gmail.com', '$2a$12$aJRVvIAm7LJIWpmn/qllROXquaJ0fYbZn/ud2sfwaSPsIifzApVJe', 12, '2024-08-14', FALSE);
INSERT INTO usuario_db.usuario (nombre, apellido, email, password, telefono, fecha_de_alta, ban) VALUES('usuario', 'usuario', 'usuario@gmail.com', '$2a$12$aJRVvIAm7LJIWpmn/qllROXquaJ0fYbZn/ud2sfwaSPsIifzApVJe', 123, '2024-08-14', FALSE);
INSERT INTO usuario_db.usuario (nombre, apellido, email, password, telefono, fecha_de_alta, ban) VALUES('Juan', 'Pérez', 'juan.perez@example.com', '$2a$12$aJRVvIAm7LJIWpmn/qllROXquaJ0fYbZn/ud2sfwaSPsIifzApVJe', 123456789, '2024-01-01', FALSE);
INSERT INTO usuario_db.usuario (nombre, apellido, email, password, telefono, fecha_de_alta, ban) VALUES('Maria', 'Gomez', 'Maria@example.com', '$2a$12$aJRVvIAm7LJIWpmn/qllROXquaJ0fYbZn/ud2sfwaSPsIifzApVJe', 12349050, '2024-08-14', FALSE);

--Insert cuentas
INSERT INTO usuario_db.cuenta (saldo) VALUES(999999.99);
INSERT INTO usuario_db.cuenta (saldo) VALUES(999999.99);

--Insert relacion usuario con cuentas
INSERT INTO usuario_db.cuenta_usuario (usuario_id, cuenta_id) VALUES(3, 1);
INSERT INTO usuario_db.cuenta_usuario (usuario_id, cuenta_id) VALUES(3, 2);
INSERT INTO usuario_db.cuenta_usuario (usuario_id, cuenta_id) VALUES(4, 2);
INSERT INTO usuario_db.cuenta_usuario (usuario_id, cuenta_id) VALUES(5, 1);

--Insert roles
INSERT INTO usuario_db.authority (name) VALUES ('ADMIN');
INSERT INTO usuario_db.authority (name) VALUES ('MANTENIMIENTO');
INSERT INTO usuario_db.authority (name) VALUES ('USUARIO');

--Insert relacion usuario con roles
INSERT INTO usuario_db.user_authority (user_id, authority_name) VALUES (1, 'ADMIN');
INSERT INTO usuario_db.user_authority (user_id, authority_name) VALUES (2, 'MANTENIMIENTO');
INSERT INTO usuario_db.user_authority (user_id, authority_name) VALUES (3, 'USUARIO');
INSERT INTO usuario_db.user_authority (user_id, authority_name) VALUES (4, 'USUARIO');
INSERT INTO usuario_db.user_authority (user_id, authority_name) VALUES (5, 'USUARIO');
