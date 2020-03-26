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
insert into track(title, performer, duration, album, publicationDate) values ("Welcome to the Black Parade 3", "My Chemical Romance", 253, "The Black Parade", curdate());

insert into track(title, performer, duration, album) values ("New song 1", "Paramore", 339, "Paramore");
insert into track(title, performer, duration, album) values ("New song 2", "Paramore", 339, "Paramore");
insert into track(title, performer, duration, album) values ("New song 3", "Paramore", 339, "Paramore");
insert into track(title, performer, duration, album) values ("New song 4", "Paramore", 339, "Paramore");
insert into track(title, performer, duration, album) values ("New song 5", "Paramore", 339, "Paramore");
insert into track(title, performer, duration, album) values ("New song 6", "Paramore", 339, "Paramore");

ALTER TABLE playlisttracks ADD FOREIGN KEY (trackId) REFERENCES track(id);
ALTER TABLE playlisttracks ADD FOREIGN KEY (playlistId) REFERENCES playlist(id);
drop table playlisttracks;

insert into playlisttracks values (1, 1);
insert into playlisttracks values (1, 2);
insert into playlisttracks values (2, 2);
insert into playlisttracks values (3, 3);
insert into playlisttracks values (4, 4);
insert into playlisttracks values (5, 1);
insert into playlisttracks values (6, 2);
insert into playlisttracks values (7, 3);
insert into playlisttracks values (8, 4);
insert into playlisttracks values (9, 1);
insert into playlisttracks values (10, 2);
insert into playlisttracks values (11, 3);
insert into playlisttracks values (12, 4);
insert into playlisttracks values (13, 1);
insert into playlisttracks values (14, 2);
insert into playlisttracks values (15, 3);
insert into playlisttracks values (16, 4);
insert into playlisttracks values (17, 1);
insert into playlisttracks values (18, 2);
insert into playlisttracks values (18, 1);

insert into user(name, password, token) values ("Alex", "password1234", "1234-1234");
insert into user(name, password, token) values ("Alex2", "password1234", "1234-1234");
drop table playlisttracks;

CREATE TABLE playlisttracks (
    trackId int not null,
    playlistId int not null,
    foreign key (trackId) references track(id) on update cascade on delete cascade,
    foreign key (playlistId) references playlist(id) on update cascade on delete cascade
    );
    
ALTER TABLE playlist
ADD CONSTRAINT FK_playlistOwner
FOREIGN KEY (owner) REFERENCES user(id);

delete from playlist where id = 4;

delete from playlist where id = 14;
select * from playlist;
select * from track;
select * from user;
select * from playlisttracks;
select * from track where playlistId != 3;
delete from track where playlistId = null;
select SUM(duration) from track;
select * from track where playlistId = 2;
select * from playlisttracks where playlistId = 2;
use spotitube;

select * from track where id not in (
	select t.id 
	from track t inner join playlisttracks pt on t.id = pt.trackId 
	where pt.playlistId = 3
);

SELECT *
FROM playlisttracks where playlistId = 1;

select t.*
from playlisttracks pt inner join track t on pt.trackId = t.id
where pt.playlistId = 3;

update playlist set owner = 3 where owner = 2;
select t.*
from playlisttracks pt
inner join track t on pt.trackId = t.id 
inner join playlist p on pt.playlistId = p.id
where p.id != 1;

ALTER TABLE track ADD FOREIGN KEY (playlistId) REFERENCES playlist(id);
update playlist set name = "123" where id = 17;
update track set playlistId = 2 where playlistId = NULL;

delete from playlisttracks where playlistId = 4 AND trackId = 16;

insert into playlist(name, owner) values ("test playlist", "469ee805-2bb5-46ac-8830-79ae9e9886f1");
select id from user where token = "469ee805-2bb5-46ac-8830-79ae9e9886f1";

alter table playlist drop foreign key `FK_playlistOwner`;
alter table playlist add foreign key (owner) references user(token);
