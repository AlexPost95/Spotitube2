insert into playlist(name, tracks) values("test playlist", "tracks");
insert into playlist values(2, "test playlist 2", false, "nog meer tracks");
insert into playlist values(3, "Nog een playlist", true, "Tracks tracks tracks");
insert into playlist values(4, "Nog een playlist", true, "Tracks tracks tracks");
insert into playlist values(5, "Nog een playlist", true, "Tracks tracks tracks");
insert into playlist values(6, "Nog een playlist", true, "Tracks tracks tracks");

insert into track(title, performer, duration, album) values ("Still Into You", "Paramore", 339, "Paramore");
insert into track(title, performer, duration, album) values ("Playing God", "Paramore", 182, "Brand New Eyes");
insert into track(title, performer, duration, album) values ("Brick By Boring Brick", "Paramore", 253, "Brand New Eyes");
insert into track(title, performer, duration, album) values ("Dance, Dance", "Fall Out Boy", 253, "From Under the Cork Tree");
insert into track(title, performer, duration, album, playlistId) values ("Welcome to the Black Parade", "My Chemical Romance", 253, "The Black Parade", 2);


delete from playlist where id = 7;

delete from playlist where id > 0;
select * from playlist;
select * from track;
select * from track where playlistId = 2;
use spotitube;

ALTER TABLE track ADD FOREIGN KEY (playlistId) REFERENCES playlist(id);
