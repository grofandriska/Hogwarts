INSERT INTO building (name,number_of_rooms) VALUES ('Madonna Temple',5);
INSERT INTO building (name,number_of_rooms) VALUES ('Darth Vader Temple',10);

INSERT INTO picture (name, building_id) VALUES ('Big picture', 1);
INSERT INTO picture (name, building_id) VALUES ('Small picture', 2);
INSERT INTO picture (name, building_id) VALUES ('Medium picture', 1);
INSERT INTO picture (name, building_id) VALUES ('Very Big picture', 1);

INSERT INTO room (room_number, capacity, number_of_beds, building_id) VALUES (1, 10, 10, 1);
INSERT INTO room (room_number, capacity, number_of_beds, building_id) VALUES (2, 5, 5, 1);
INSERT INTO room (room_number, capacity, number_of_beds, building_id) VALUES (1, 1, 1, 2);


INSERT INTO student (name, house_type, pet_type, room_id, pure_blood) VALUES ('Jancsika', 'GRYFFINDOR', 'CAT', 1, true);
INSERT INTO student (name, house_type, pet_type, room_id, pure_blood) VALUES ('Juliska', 'HUFFLEPUFF', 'NONE', 1, true);
INSERT INTO student (name, house_type, pet_type, room_id, pure_blood) VALUES ('Farkas', 'HUFFLEPUFF', 'NONE', 1, true);
INSERT INTO student (name, house_type, pet_type, room_id, pure_blood) VALUES ('Petike', 'RAVENCLAW', 'RAT', 2, true);
INSERT INTO student (name, house_type, pet_type, room_id, pure_blood) VALUES ('Maria', 'SLYTHERIN', 'RAT', 2, true);
INSERT INTO student (name, house_type, pet_type, room_id, pure_blood) VALUES ('Gergő', 'SLYTHERIN', 'RAT', 3, true);

INSERT INTO recipe (name, ing1, ing2, ing3, ing4, ing5) VALUES ('spiderman', 'DOG_EAR', 'BIRD_HAIR', 'CAT_TAIL', 'CAT_TAIL', 'ELEPHANT_TUSK');
INSERT INTO recipe (name, ing1, ing2, ing3, ing4, ing5) VALUES ('madonna', 'DOG_EAR', 'BIRD_HAIR', 'MADONNA_ARMPIT_HAIR', 'DONKEY_HOOF', 'ELEPHANT_TUSK');
INSERT INTO recipe (name, ing1, ing2, ing3, ing4, ing5) VALUES ('ironman',  'CAT_TAIL', 'BIRD_HAIR', 'BIRD_FEATHERS', 'DONKEY_HOOF', 'ELEPHANT_TUSK');
INSERT INTO recipe (name, ing1, ing2, ing3, ing4, ing5) VALUES ('kokain', 'DOG_EAR', 'BIRD_HAIR', 'MADONNA_ARMPIT_HAIR', 'BAT_BLOOD', 'ELEPHANT_TUSK');
INSERT INTO recipe (name, ing1, ing2, ing3, ing4, ing5) VALUES ('speed', 'GOAT_EXCREMENT', 'BIRD_HAIR', 'MADONNA_ARMPIT_HAIR', 'DONKEY_HOOF', 'DOG_EAR');

INSERT INTO student_potion (student_id, potion_id) VALUES (1, 1);
INSERT INTO student_potion (student_id, potion_id) VALUES (1, 2);
INSERT INTO student_potion (student_id, potion_id) VALUES (2, 2);
INSERT INTO student_potion (student_id, potion_id) VALUES (2, 5);
INSERT INTO student_potion (student_id, potion_id) VALUES (3, 2);
INSERT INTO student_potion (student_id, potion_id) VALUES (4, 2);

INSERT INTO spell (name, power, banned) VALUES ('LUMOS', 8, false);
INSERT INTO spell (name, power, banned) VALUES ('LEVITATE', 3, false);
INSERT INTO spell (name, power, banned) VALUES ('FLY', 4, false);
INSERT INTO spell (name, power, banned) VALUES ('ABRAKADABRA', 10, true);

INSERT INTO wand (wood_type, color_type) VALUES ('BUTTERNUT', 'RED');
INSERT INTO wand (wood_type, color_type) VALUES ('BLACK_CHERRY', 'RAINBOW');
INSERT INTO wand (wood_type, color_type) VALUES ('BLACK_CHERRY', 'BLUE');
INSERT INTO wand (wood_type, color_type) VALUES ('PIN_CHERRY', 'BLUE');
INSERT INTO wand (wood_type, color_type) VALUES ('AMERICAN_CHESTNUT', 'BROWN');
INSERT INTO wand (wood_type, color_type) VALUES ('AMERICAN_CHESTNUT', 'YELLOW');
INSERT INTO wand (wood_type, color_type) VALUES ('BUTTERNUT', 'WHITE');
INSERT INTO wand (wood_type, color_type) VALUES ('BUTTERNUT', 'GREY');

INSERT INTO teacher (name, subject, witch, age, wand_id) VALUES ('Hamar Gyuri', 'DARK_SPELLS', true, 89, 2);
INSERT INTO teacher (name, subject, witch, age, wand_id) VALUES ('Krausz Marcell', 'HISTORY', false, 10, 4);
INSERT INTO teacher (name, subject, witch, age, wand_id) VALUES ('Horváth Télapó Miklós', 'SPELLS', true, 50, 1);
INSERT INTO teacher (name, subject, witch, age, wand_id) VALUES ('Ádám Irmai', 'MATH', true, 120, 3);

INSERT INTO wand_spell VALUES (1, 3);
INSERT INTO wand_spell VALUES (2, 4);
INSERT INTO wand_spell VALUES (3, 1);
INSERT INTO wand_spell VALUES (4, 2);
