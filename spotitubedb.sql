USE master
GO
DROP DATABASE IF EXISTS Spotitube
GO
CREATE database Spotitube
GO
USE Spotitube
GO

CREATE USER Spotitube FOR LOGIN Spotitube
    EXEC sp_addrolemember N'db_owner', N'Spotitube'

CREATE TABLE spotitubeUser (
  USERNAME char(16) NOT NULL PRIMARY KEY,
  USERHASH char(255) NOT NULL,
  token char(6) DEFAULT NULL
)
GO

CREATE TABLE playlist (
  PLAYLISTID int NOT NULL IDENTITY(1,1) PRIMARY KEY,
  PLAYLISTNAME char(64) NOT NULL,
  USERNAME char(16) NOT NULL
)
GO
ALTER TABLE playlist
  ADD CONSTRAINT FK_OWNER FOREIGN KEY (USERNAME) REFERENCES spotitubeUser (USERNAME) ON DELETE CASCADE ON UPDATE CASCADE;

GO
CREATE TABLE track (
  TRACKID int NOT NULL IDENTITY(1,1) PRIMARY KEY,
  TITLE char(64) NOT NULL,
  PERFORMER char(64) NOT NULL,
  DURATION int NOT NULL,
  ALBUM char(64) NOT NULL,
  PUBLICATIONDATE date DEFAULT NULL,
  DESCRIPTION text DEFAULT NULL
)
GO

CREATE TABLE track_in_playlist (
  PLAYLISTID int NOT NULL,
  TRACKID int NOT NULL,
  OFFLINEAVAILABLE tinyint NOT NULL,
  PLAYCOUNT int DEFAULT NULL
)
GO
ALTER TABLE track_in_playlist
	ADD PRIMARY KEY (PLAYLISTID,TRACKID);
ALTER TABLE track_in_playlist
	ADD CONSTRAINT FK_track_in_playlist_playlist
	FOREIGN KEY (PLAYLISTID)
	REFERENCES playlist (PLAYLISTID)
	ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE track_in_playlist
	ADD CONSTRAINT FK_track_in_playlist_track
	FOREIGN KEY (TRACKID)
	REFERENCES track (TRACKID)
	ON DELETE CASCADE ON UPDATE CASCADE;

GO
INSERT INTO spotitubeUser(USERNAME, USERHASH, token)
VALUES('gert', 'sdwd', '3435');

GO
USE master
GO