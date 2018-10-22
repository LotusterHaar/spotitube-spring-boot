DROP TABLE IF EXISTS `spotitube`.`tracks_view`;
USE `spotitube`;
CREATE OR REPLACE VIEW `tracks_view` AS
  SELECT track.id,
         track.title,
         track.performer,
         track.duration,
         track.playcount,
         track.offlineAvailable,
         songtrack.album,
         videotrack.publicationDate,
         videotrack.description
  FROM track
         LEFT JOIN songtrack
           ON songtrack.id = track.song_or_video;
         LEFT JOIN videotrack
           ON videotrack.id = track.song_or_video;
           
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;