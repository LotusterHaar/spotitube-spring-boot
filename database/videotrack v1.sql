CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `spotitube`.`videotracks_view` AS
    SELECT 
        `spotitube`.`track`.`id` AS `id`,
        `spotitube`.`track`.`title` AS `title`,
        `spotitube`.`track`.`performer` AS `performer`,
        `spotitube`.`track`.`duration` AS `duration`,
        `spotitube`.`track`.`playcount` AS `playcount`,
        `spotitube`.`track`.`offlineAvailable` AS `offlineAvailable`,
        `spotitube`.`videotrack`.`publicationDate` AS `publicationDate`,
        `spotitube`.`videotrack`.`description` AS `description`
    FROM
        (`spotitube`.`videotrack`
        LEFT JOIN `spotitube`.`track` ON ((`spotitube`.`videotrack`.`id` = `spotitube`.`track`.`id`)))