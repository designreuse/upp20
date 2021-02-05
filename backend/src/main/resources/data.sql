
DELETE FROM literary_association_db.publishers ^;
DELETE FROM literary_association_db.reader_favorite_genres ^;
DELETE FROM literary_association_db.reader_beta_access_genres ^;
DELETE FROM literary_association_db.author_favorite_genres ^;
DELETE FROM literary_association_db.membership_requests_submitted_documents ^;
DELETE FROM literary_association_db.membership_reviews ^;
DELETE FROM literary_association_db.membership_requests ^;
DELETE FROM literary_association_db.users_board_members ^;
DELETE FROM literary_association_db.users_system_admins ^;
DELETE FROM literary_association_db.users_proofreaders ^;
DELETE FROM literary_association_db.users_editors ^;
DELETE FROM literary_association_db.users_readers ^;
DELETE FROM literary_association_db.users_beta_access_readers ^;
DELETE FROM literary_association_db.users_authors ^;
DELETE FROM literary_association_db.genres ^;
DELETE FROM literary_association_db.addresses ^;
DELETE FROM literary_association_db.verification_codes ^;
DELETE FROM literary_association_db.membership_requests_submitted_documents ^;
DELETE FROM literary_association_db.documents ^;
DELETE FROM literary_association_db.membership_requests ^;

INSERT INTO literary_association_db.genres (id, name)
VALUES
    (0, 'Action'),
    (1, 'Adventure'),
    (2, 'Classic'),
    (3, 'Comic Book'),
    (4, 'Graphic Novel'),
    (5, 'Mystery'),
    (6, 'Fantasy'),
    (7, 'Historical Fiction'),
    (8, 'Horror'),
    (9, 'Romance'),
    (10, 'Literary Fiction'),
    (11, 'SciFi'),
    (12, 'Short Stories'),
    (13, 'Thriller'),
    (14, 'Biography'),
    (15, 'Cookbook'),
    (16, 'Memoir'),
    (17, 'History'),
    (18, 'Poetry'),
    (19, 'Self Help'),
    (20, 'True Crime') ^;

INSERT INTO literary_association_db.addresses (id, city, country, latitude, longitude, postal_code, street)
VALUES
    (22, 'Novi Sad', 'Serbia', 45.2461012, 19.8495081, '21000', 'Trg Dositeja Obradovica 6'),
    (25, 'Novi Sad', 'Serbia', 45.2461012, 19.8495081, '21000', 'Trg Dositeja Obradovica 6'),
    (28, 'Novi Sad', 'Serbia', 45.2461012, 19.8495081, '21000', 'Trg Dositeja Obradovica 6'),
    (31, 'Novi Sad', 'Serbia', 45.2461012, 19.8495081, '21000', 'Trg Dositeja Obradovica 6'),
    (34, 'Novi Sad', 'Serbia', 45.2461012, 19.8495081, '21000', 'Trg Dositeja Obradovica 6'),
    (37, 'Novi Sad', 'Serbia', 45.2461012, 19.8495081, '21000', 'Trg Dositeja Obradovica 6'),
    (40, 'Novi Sad', 'Serbia', 45.2461012, 19.8495081, '21000', 'Trg Dositeja Obradovica 6'),
    (43, 'Novi Sad', 'Serbia', 45.2461012, 19.8495081, '21000', 'Trg Dositeja Obradovica 6'),
    (46, 'Novi Sad', 'Serbia', 45.2461012, 19.8495081, '21000', 'Trg Dositeja Obradovica 6'),
    (49, 'Novi Sad', 'Serbia', 45.2461012, 19.8495081, '21000', 'Trg Dositeja Obradovica 6'),
    (52, 'Novi Sad', 'Serbia', 45.2461012, 19.8495081, '21000', 'Trg Dositeja Obradovica 6'),
    (55, 'Novi Sad', 'Serbia', 45.2461012, 19.8495081, '21000', 'Trg Dositeja Obradovica 6'),
    (58, 'Novi Sad', 'Serbia', 45.2461012, 19.8495081, '21000', 'Trg Dositeja Obradovica 6'),
    (61, 'Novi Sad', 'Serbia', 45.2461012, 19.8495081, '21000', 'Trg Dositeja Obradovica 6'),
    (64, 'Novi Sad', 'Serbia', 45.2461012, 19.8495081, '21000', 'Trg Dositeja Obradovica 6'),
    (67, 'Novi Sad', 'Serbia', 45.2461012, 19.8495081, '21000', 'Trg Dositeja Obradovica 6'),
    (70, 'Novi Sad', 'Serbia', 45.2461012, 19.8495081, '21000', 'Trg Dositeja Obradovica 6'),
    (73, 'Novi Sad', 'Serbia', 45.2461012, 19.8495081, '21000', 'Trg Dositeja Obradovica 6'),
    (76, 'Novi Sad', 'Serbia', 45.2461012, 19.8495081, '21000', 'Trg Dositeja Obradovica 6')
    ^;

INSERT INTO literary_association_db.verification_codes (id, generated_at, status, value)
VALUES
    (23, NOW(), 'USED', UUID()),
    (26, NOW(), 'USED', UUID()),
    (29, NOW(), 'USED', UUID()),
    (32, NOW(), 'USED', UUID()),
    (35, NOW(), 'USED', UUID()),
    (38, NOW(), 'USED', UUID()),
    (41, NOW(), 'USED', UUID()),
    (44, NOW(), 'USED', UUID()),
    (47, NOW(), 'USED', UUID()),
    (50, NOW(), 'USED', UUID()),
    (53, NOW(), 'USED', UUID()),
    (56, NOW(), 'USED', UUID()),
    (59, NOW(), 'USED', UUID()),
    (62, NOW(), 'USED', UUID()),
    (65, NOW(), 'USED', UUID()),
    (68, NOW(), 'USED', UUID()),
    (71, NOW(), 'USED', UUID()),
    (74, NOW(), 'USED', UUID())
    ^;

INSERT INTO literary_association_db.users_board_members (id, email, first_name, is_verified, last_name, password, role, username, address_id, verification_code_id)
VALUES
    (21, 'f.ivkovic16+21@gmail.com', 'Board', TRUE, 'Member A', '$2a$10$FNxNK9DH1b5Ts2SYw.6n0uwZMm.cwrUpZdykQhRJ4d1WEsNQqeVte', 'BOARD_MEMBER', 'boardmember1', 22, 23),
    (24, 'f.ivkovic16+24@gmail.com', 'Board', TRUE, 'Member B', '$2a$10$FNxNK9DH1b5Ts2SYw.6n0uwZMm.cwrUpZdykQhRJ4d1WEsNQqeVte', 'BOARD_MEMBER', 'boardmember2', 25, 26),
    (27, 'f.ivkovic16+27@gmail.com', 'Board', TRUE, 'Member C', '$2a$10$FNxNK9DH1b5Ts2SYw.6n0uwZMm.cwrUpZdykQhRJ4d1WEsNQqeVte', 'BOARD_MEMBER', 'boardmember3', 28, 29),
    (30, 'f.ivkovic16+30@gmail.com', 'Board', TRUE, 'Member D', '$2a$10$FNxNK9DH1b5Ts2SYw.6n0uwZMm.cwrUpZdykQhRJ4d1WEsNQqeVte', 'BOARD_MEMBER', 'boardmember4', 31, 32),
    (33, 'f.ivkovic16+33@gmail.com', 'Board', TRUE, 'Member E', '$2a$10$FNxNK9DH1b5Ts2SYw.6n0uwZMm.cwrUpZdykQhRJ4d1WEsNQqeVte', 'BOARD_MEMBER', 'boardmember5', 34, 35) ^;

INSERT INTO literary_association_db.users_system_admins (id, email, first_name, is_verified, last_name, password, role, username, address_id, verification_code_id)
VALUES
    (36, 'f.ivkovic16+36@gmail.com', 'System', TRUE, 'Administrator', '$2a$10$FNxNK9DH1b5Ts2SYw.6n0uwZMm.cwrUpZdykQhRJ4d1WEsNQqeVte', 'SYSTEM_ADMIN', 'admin', 37, 38) ^;

INSERT INTO literary_association_db.users_proofreaders (id, email, first_name, is_verified, last_name, password, role, username, address_id, verification_code_id)
VALUES
    (39, 'f.ivkovic16+39@gmail.com', 'Proof', TRUE, 'Reader', '$2a$10$FNxNK9DH1b5Ts2SYw.6n0uwZMm.cwrUpZdykQhRJ4d1WEsNQqeVte', 'PROOFREADER', 'proofreader', 40, 41) ^;

INSERT INTO literary_association_db.users_editors (id, email, first_name, is_verified, last_name, password, role, username, address_id, verification_code_id)
VALUES
    (42, 'f.ivkovic16+42@gmail.com', 'Editor', TRUE, 'A', '$2a$10$FNxNK9DH1b5Ts2SYw.6n0uwZMm.cwrUpZdykQhRJ4d1WEsNQqeVte', 'EDITOR', 'editor1', 43, 44),
    (45, 'f.ivkovic16+45@gmail.com', 'Editor', TRUE, 'B', '$2a$10$FNxNK9DH1b5Ts2SYw.6n0uwZMm.cwrUpZdykQhRJ4d1WEsNQqeVte', 'EDITOR', 'editor2', 46, 47),
    (48, 'f.ivkovic16+48@gmail.com', 'Editor', TRUE, 'C', '$2a$10$FNxNK9DH1b5Ts2SYw.6n0uwZMm.cwrUpZdykQhRJ4d1WEsNQqeVte', 'EDITOR', 'editor3', 49, 50),
    (51, 'f.ivkovic16+51@gmail.com', 'Editor', TRUE, 'D', '$2a$10$FNxNK9DH1b5Ts2SYw.6n0uwZMm.cwrUpZdykQhRJ4d1WEsNQqeVte', 'EDITOR', 'editor4', 52, 53),
    (54, 'f.ivkovic16+54@gmail.com', 'Editor', TRUE, 'E', '$2a$10$FNxNK9DH1b5Ts2SYw.6n0uwZMm.cwrUpZdykQhRJ4d1WEsNQqeVte', 'EDITOR', 'editor5', 55, 56),
    (57, 'f.ivkovic16+57@gmail.com', 'Editor', TRUE, 'F', '$2a$10$FNxNK9DH1b5Ts2SYw.6n0uwZMm.cwrUpZdykQhRJ4d1WEsNQqeVte', 'EDITOR', 'editor6', 58, 59),
    (60, 'f.ivkovic16+60@gmail.com', 'Editor', TRUE, 'G', '$2a$10$FNxNK9DH1b5Ts2SYw.6n0uwZMm.cwrUpZdykQhRJ4d1WEsNQqeVte', 'EDITOR', 'editor7', 61, 62),
    (63, 'f.ivkovic16+63@gmail.com', 'Editor', TRUE, 'H', '$2a$10$FNxNK9DH1b5Ts2SYw.6n0uwZMm.cwrUpZdykQhRJ4d1WEsNQqeVte', 'EDITOR', 'editor8', 64, 65),
    (66, 'f.ivkovic16+66@gmail.com', 'Editor', TRUE, 'In-Chief', '$2a$10$FNxNK9DH1b5Ts2SYw.6n0uwZMm.cwrUpZdykQhRJ4d1WEsNQqeVte', 'EDITOR_IN_CHIEF', 'chiefeditor', 67, 68) ^;

INSERT INTO literary_association_db.users_readers (id, email, first_name, is_verified, last_name, password, role, username, address_id, verification_code_id, has_requested_beta_access, membership_status)
VALUES
    (69, 'f.ivkovic16+69@gmail.com', 'Jon', TRUE, 'Skeet', '$2a$10$FNxNK9DH1b5Ts2SYw.6n0uwZMm.cwrUpZdykQhRJ4d1WEsNQqeVte', 'READER', 'reader', 70, 71, FALSE, 'ACTIVE') ^;

INSERT INTO literary_association_db.users_beta_access_readers (id, email, first_name, is_verified, last_name, password, role, username, address_id, verification_code_id, has_requested_beta_access, membership_status, penalty_points_count)
VALUES
    (72, 'f.ivkovic16+72@gmail.com', 'Beta', TRUE, 'Skeet', '$2a$10$FNxNK9DH1b5Ts2SYw.6n0uwZMm.cwrUpZdykQhRJ4d1WEsNQqeVte', 'BETA_ACCESS_READER', 'betareader', 73, 74, FALSE, 'ACTIVE', 0) ^;

INSERT INTO literary_association_db.reader_favorite_genres (reader_id, genre_id)
VALUES
    (69, 0),
    (69, 1),
    (69, 2),
    (72, 17),
    (72, 18),
    (72, 19),
    (72, 20) ^;

INSERT INTO literary_association_db.reader_beta_access_genres (beta_access_reader_id, genre_id)
VALUES
    (72, 18),
    (72, 19),
    (72, 20) ^;

INSERT INTO literary_association_db.publishers (`id`, `name`, `address_id`)
VALUES
    (75, 'LASS Publishing', 76) ^;

UPDATE literary_association_db.hibernate_sequence
SET next_val = 1000 ^;


