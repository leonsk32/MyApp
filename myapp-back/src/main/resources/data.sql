delete from accounts;

insert into accounts values (
    'user',
    'user@example.com',
    'NORMAL_USER',
    '$2a$10$hj1yHIE7z2Mznbhrs/0hC.QpiEi9eWqRLUBtUCJimxHWK9eMPRMlm',
    NULL,
    NULL
),
(
    'admin',
    'admin@example.com',
    'ADMIN',
    '$2a$08$K1daVeSmt8z9oD7DADMNK.YVYawyXntaZZywKUxTk1S7tJyg8tRzW',
    NULL,
    NULL
);