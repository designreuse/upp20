DELETE FROM literary_association_db.users_readers ^;
DELETE FROM literary_association_db.users_beta_access_readers ^;
DELETE FROM literary_association_db.reader_favorite_genres ^;
DELETE FROM literary_association_db.reader_beta_access_genres ^;
DELETE FROM literary_association_db.genres ^;
INSERT INTO literary_association_db.genres (id, name)
VALUES
    (9000, 'Action'), (9001, 'Adventure'), (9002, 'Classic'), (9003, 'Comic Book'), (9004, 'Graphic Novel'),
    (9005, 'Mystery'), (9006, 'Fantasy'), (9007, 'Historical Fiction'), (9008, 'Horror'), (9009, 'Romance'),
    (9010, 'Literary Fiction'), (9011, 'SciFi'), (9012, 'Short Stories'), (9013, 'Thriller'), (9014, 'Biography'),
    (9015, 'Cookbook'), (9016, 'Memoir'), (9017, 'History'), (9018, 'Poetry'), (9019, 'Self Help'), (9020, 'True Crime') ^;


