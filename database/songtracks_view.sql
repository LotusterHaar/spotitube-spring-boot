CREATE VIEW `spotitube`.`songtracks_view` AS
  SELECT 
        `spotitube`.`track`.`id` AS `id`,
        `spotitube`.`track`.`title` AS `title`,
        `spotitube`.`track`.`performer` AS `performer`,
        `spotitube`.`track`.`duration` AS `duration`,
        `spotitube`.`track`.`playcount` AS `playcount`,
        `spotitube`.`track`.`offlineAvailable` AS `offlineAvailable`,
        `spotitube`.`songtrack`.`album` AS `album`
    FROM `spotitube`.`track`
        LEFT JOIN `spotitube`.`songtrack` ON `spotitube`.`songtrack`.`id` = `spotitube`.`track`.`id`