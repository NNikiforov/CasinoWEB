CREATE TRIGGER `update_money` AFTER INSERT ON `casinodb`.`bets`
FOR EACH ROW
BEGIN 
UPDATE `casinodb`.`croupiers`
SET `yield` = (`yield` - NEW.`profit`)
WHERE `bets`.`id` = NEW.`id`;
END;
