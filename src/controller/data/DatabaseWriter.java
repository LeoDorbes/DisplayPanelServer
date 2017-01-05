package controller.data;

import java.sql.SQLException;



public class DatabaseWriter {

		public void addMatch(String home, String guest, int homeScore, int guestScore){
			DBAction.DBConnexion();
			
			String req = ( "INSERT INTO finished_game (home_team,guest_team,home_score,guest_score) "
					+ "VALUES ('" +home + "','" + guest + "','"+ homeScore + "','"+ guestScore + "')" );
			try {
				DBAction.getStm().executeUpdate(req);
			} catch (SQLException ex) {
				System.out.println("Game add SQL Error : " + ex.getErrorCode());
				System.out.println(ex.getMessage());
			}
			DBAction.DBClose();
		}
}
