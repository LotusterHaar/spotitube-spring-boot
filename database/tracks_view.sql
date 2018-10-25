CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `spotitube`.`tracks_view` AS
    SELECT 
        `spotitube`.`track`.`id` AS `id`,
        `spotitube`.`track`.`title` AS `title`,
        `spotitube`.`track`.`performer` AS `performer`,
        `spotitube`.`track`.`duration` AS `duration`,
        `spotitube`.`track`.`playcount` AS `playcount`,
        `spotitube`.`track`.`offlineAvailable` AS `offlineAvailable`,
        `spotitube`.`songtrack`.`album` AS `album`,
        `spotitube`.`videotrack`.`publicationDate` AS `publicationDate`,
        `spotitube`.`videotrack`.`description` AS `description`
    FROM
        ((`spotitube`.`track`
        LEFT JOIN `spotitube`.`songtrack` ON ((`spotitube`.`songtrack`.`id` = `spotitube`.`track`.`id`)))
        LEFT JOIN `spotitube`.`videotrack` ON ((`spotitube`.`videotrack`.`id` = `spotitube`.`track`.`id`)))