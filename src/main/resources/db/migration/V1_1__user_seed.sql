CREATE EXTENSION pgcrypto;
INSERT INTO users
                (id,  title, first_name, last_name, email, mobile, password, role, status, verified, date_deactivated, date_registered, date_verified)
    VALUES
	            (gen_random_uuid(), 'Mr.', 'John', 'Doe', 'test1@gmail.com', '+23465657578', gen_random_uuid(), 'USER', 'VERIFIED', true, null, now(), now()),
	            (gen_random_uuid(), 'Mr.', 'Rick', 'James', 'test2@gmail.com', '+0165556700', gen_random_uuid(), 'USER', 'REGISTERED', false, null, now(), null),
	            (gen_random_uuid(), 'Mrs.', 'Toby', 'Steven', 'test3@gmail.com', '+99300232332', gen_random_uuid(), 'USER', 'VERIFIED', true, null, now(), now()),
	            (gen_random_uuid(), 'Miss', 'Elon', 'Gates', 'test4@gmail.com', '+33356575780', gen_random_uuid(), 'USER', 'VERIFIED', true, null, now(), now()),
	            (gen_random_uuid(), 'Dr.', 'Kevin', 'Stark', 'test5@gmail.com', '+24445657578', gen_random_uuid(), 'USER', 'DEACTIVATED', true, now(), now(), now()),
	            (gen_random_uuid(), 'Prof.', 'James', 'Doe', 'test6@gmail.com', '+0956573238', gen_random_uuid(), 'ADMIN', 'VERIFIED', true, null, now(), now()),
	            (gen_random_uuid(), 'Mr.', 'Harry', 'Bond', 'test7@gmail.com', '+4563424323', gen_random_uuid(), 'ADMIN', 'REGISTERED', false, null, now(), null),
	            (gen_random_uuid(), 'Miss', 'Rebecca', 'Fish', 'test8@gmail.com', '+6662323443', gen_random_uuid(), 'ADMIN', 'VERIFIED', true, null, now(), now()),
	            (gen_random_uuid(), 'Mrs.', 'Rachael', 'Sarah', 'test9@gmail.com', '+41993829324', gen_random_uuid(), 'ADMIN', 'VERIFIED', true, null, now(), now()),
	            (gen_random_uuid(), 'Miss', 'John', 'Doe', 'test10@gmail.com', '+34355039595', gen_random_uuid(), 'ADMIN', 'DEACTIVATED', true, now(), now(), now())