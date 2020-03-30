insert into USERS (username, email, password, enabled) values (
    'admin',
    'admin@example.com',
    '$2a$10$UalEewK6Nf7CULBMUQClNe6GicCWtpQdpSvcWa6ZEX36Qrec5xhSe',
    'true'
);
insert into AUTHORITIES (id, name) values (
    1,
    'ROLE_ADMIN'
);
insert into USER_AUTHORITIES (user_id, authority_id) values (
    1,
    1
);
