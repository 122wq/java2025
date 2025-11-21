public class waterFlowing
{
    
	//Pre-condition: nothing is null, row is less than map[0].length, col is less than map.length1
	//Post-condition: return true or false based on possiblity of reaching the edge
    public static boolean canFlowOff(int[][] map, int row, int col)
    {
		//the location as given by row and col 
		int current = map[row][col];
        if (row > 0 && col > 0 && row < map[0].length - 1 && col < map.length - 1)
        {
			//Checking every possible path going in one direction, return true if it can reach the edge, otherwise move on to other directions until all directions are searched
			//Up 
            if (map[row - 1][col] < current && canFlowOff(map, row - 1, col)) {
       	 		return true;
    		}

    		//Down
    		if (map[row + 1][col] < current && canFlowOff(map, row + 1, col)) {
        		return true;
    		}

    		//Left
    		if (map[row][col - 1] < current && canFlowOff(map, row, col - 1)) {
        		return true;
    		}

    		//Right
    		if (map[row][col + 1] < current && canFlowOff(map, row, col + 1)) {
	        	return true;
	    	}
			return false;
        }
		//return true if the starting location is on the edge of map
        return true;
    }
    public static void main(String[] args) {
        int[][] map = {
		{100, 200, 200, 200, 200, 200, 200, 200, 200, 200}, 
		{200, 200, 200, 200, 200, 200, 200, 200, 200, 200},
		{200, 10, 200, 200, 200, 200, 200, 200, 200, 200},
		{200, 11, 10, 200, 200, 6, 85, 84, 83, 200},
		{200, 200, 14, 15, 59, 200, 86, 200, 82, 200},
		{200, 11, 12, 200, 200, 200, 87, 200, 81, 200},
		{200, 10, 200, 200, 90, 89, 88, 200, 200, 200},
		{200, 9, 8, 200, 200, 200, 200, 200, 200, 200},
		{200, 200, 7, 200, 200, 200, 200, 200, 200, 200},
		{200, 98, 6, 200, 200, 200, 200, 200, 200, 200}
	};

	int[][] map1 = {
		{100, 200, 200, 200, 200, 200, 200, 200, 200, 200}, 
		{200, 200, 200, 200, 200, 200, 200, 200, 200, 200},
		{200, 10, 200, 200, 200, 200, 200, 200, 200, 200},
		{200, 11, 10, 200, 200, 6, 85, 84, 83, 200},
		{200, 200, 14, 15, 59, 200, 86, 200, 82, 200},
		{200, 11, 12, 2, 200, 200, 87, 200, 81, 200},
		{200, 10, 200, 3, 90, 89, 88, 86, 80, 200},
		{200, 9, 8, 200, 4, 200, 200, 85, 200, 200},
		{200, 200, 7, 6, 5, 200, 200, 200, 200, 200},
		{200, 98, 64, 200, 200, 200, 200, 200, 200, 200}
	}; 
	System.out.println(canFlowOff(map, 4, 5));
	System.out.println(canFlowOff(map1, 5, 6));
    }
}
