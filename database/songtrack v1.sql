CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `spotitube`.`songtracks_view` AS
    SELECT 
        `spotitube`.`track`.`id` AS `id`,
        `spotitube`.`track`.`title` AS `title`,
        `spotitube`.`track`.`performer` AS `performer`,
        `spotitube`.`track`.`duration` AS `duration`,
        `spotitube`.`track`.`playcount` AS `playcount`,
        `spotitube`.`track`.`offlineAvailable` AS `offlineAvailable`,
        `spotitube`.`songtrack`.`album` AS `album`
    FROM
        (`spotitube`.`songtrack`
        LEFT JOIN `spotitube`.`track` ON ((`spotitube`.`songtrack`.`id` = `spotitube`.`track`.`id`)))