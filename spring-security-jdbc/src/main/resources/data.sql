--INSERT INTO users(username, password, enabled) VALUES('user','pass',true);
--
--INSERT INTO users(username, password, enabled) VALUES('admin','passw',true);
--
--INSERT INTO authorities(username, authority) VALUES('user','ROLE_USER');
--
--INSERT INTO authorities(username, authority) VALUES('admin','ROLE_ADMIN');

INSERT INTO myusers(username, password, enabled) VALUES('user','pass',true);

INSERT INTO myusers(username, password, enabled) VALUES('admin','passw',true);

INSERT INTO myauthorities(username, authority) VALUES('user','ROLE_USER');

INSERT INTO myauthorities(username, authority) VALUES('admin','ROLE_ADMIN');