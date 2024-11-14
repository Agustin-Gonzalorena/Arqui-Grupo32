--Insert usuarios
INSERT INTO usuario_db.usuario (nombre, apellido, email, password, telefono, fecha_de_alta, rol, ban) VALUES('Juan', 'Pérez', 'juan.perez@example.com', '1234', 123456789, '2024-01-01', 'ADMIN', FALSE);
INSERT INTO usuario_db.usuario (nombre, apellido, email, password, telefono, fecha_de_alta, rol, ban) VALUES('Maria', 'Gomez', 'Maria@example.com', '1234', 12349050, '2024-08-14', 'NORMAL', FALSE);

--Insert cuentas
INSERT INTO usuario_db.cuenta (saldo) VALUES(999999.99);
INSERT INTO usuario_db.cuenta (saldo) VALUES(999999.99);

INSERT INTO usuario_db.cuenta_usuario (usuario_id, cuenta_id) VALUES(1, 1);
INSERT INTO usuario_db.cuenta_usuario (usuario_id, cuenta_id) VALUES(1, 2);
INSERT INTO usuario_db.cuenta_usuario (usuario_id, cuenta_id) VALUES(2, 2);