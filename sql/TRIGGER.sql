DROP TRIGGER IF EXISTS casinodb.update_money;
use casinodb;
DELIMITER | 
CREATE TRIGGER update_money AFTER INSERT ON casinodb.bets
	FOR EACH ROW BEGIN 
		UPDATE casinodb.croupiers AS c SET c.yield = (c.yield - NEW.profit) WHERE c.id = NEW.croupier_id;
        UPDATE casinodb.users AS u 
			SET u.cash = (u.cash + NEW.profit),
            u.rating = u.rating + 
				CASE NEW.result
					WHEN 'BIGWIN' THEN 10
                    WHEN 'WIN' THEN 5
                    WHEN 'LOSE' THEN -5
                    WHEN 'SLOSE' THEN -3
				END
            WHERE u.id = NEW.punter_id;
END;|
DELIMITER ;