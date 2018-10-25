CREATE VIEW `spotitube`.`videotracks_view` AS
  SELECT 
        `spotitube`.`track`.`id` AS `id`,
        `spotitube`.`track`.`title` AS `title`,
        `spotitube`.`track`.`performer` AS `performer`,
        `spotitube`.`track`.`duration` AS `duration`,
        `spotitube`.`track`.`playcount` AS `playcount`,
        `spotitube`.`track`.`offlineAvailable` AS `offlineAvailable`,
        `spotitube`.`videotrack`.`publicationDate` AS `publicationDate`,
        `spotitube`.`videotrack`.`description` AS `description`
    FROM `spotitube`.`track`
        LEFT JOIN `spotitube`.`videotrack` ON `spotitube`.`videotrack`.`id` = `spotitube`.`track`.`id`