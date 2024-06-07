SET @gryffindor_house_id = (SELECT id FROM hogwarts_houses WHERE name = 'Gryffindor');
SET @ravenclaw_house_id = (SELECT id FROM hogwarts_houses WHERE name = 'Ravenclaw');
SET @hufflepuff_house_id = (SELECT id FROM hogwarts_houses WHERE name = 'Hufflepuff');
SET @slytherin_house_id = (SELECT id FROM hogwarts_houses WHERE name = 'Slytherin');

INSERT INTO wizards (name, house_id, wand_type)
VALUES
  ('Harry Potter', @gryffindor_house_id, 'Phoenix feather'),
  ('Hermione Granger', @gryffindor_house_id, 'Dragon heartstring'),
  ('Cedric Diggory', @hufflepuff_house_id, 'Unicorn tail hair'),
  ('Gilderoy Lockhart', @ravenclaw_house_id, 'Dragon heartstring'),
  ('Severus Snape', @slytherin_house_id, 'Phoenix feather'),
  ('Tom Riddle', @slytherin_house_id, 'Phoenix feather')