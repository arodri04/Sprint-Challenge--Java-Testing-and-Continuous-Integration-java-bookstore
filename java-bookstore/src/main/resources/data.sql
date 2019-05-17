DELETE
FROM bookauthors;

DELETE
FROM book;

DELETE
FROM author;

INSERT INTO author (authorid, firstname, lastname)
    VALUES (1, 'Micheal', 'Crichton' ),
           (2, 'Kyle', 'Ren');

INSERT INTO book (bookid, booktitle, isbn, copy)
    VALUES (1, 'Jurassic Park', 'WTFISANISBN', 1980),
           (2, 'Jurassic Park TheLostWorld', 'WTFISANISBN', 1980),
           (3, 'Patricide101', 'WTFISANISBN', 1000),
           (4, 'Dragon Tooth', 'ANOTHERISBN', 2016),
           (5, 'Pirate Latitudes', 'ANOTHERISBN', 2011),
           (6, 'Congo', 'ANOTHERISBN', 1980);


INSERT INTO bookauthors (bookid, authorid)
VALUES (1, 1),
       (2, 1),
       (3, 2), (4,1), (5, 1), (6, 1);


alter sequence hibernate_sequence restart with 20;