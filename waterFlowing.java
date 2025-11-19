public class waterFlowing
{
    public waterFlowing()
    {

    }

    public static boolean canFlowOff(int[][] map, int row, int col)
    {
		int current = map[row][col];
        if (row > 0 && col > 0 && row < map[0].length - 1 && col < map.length - 1)
        {
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
 
	System.out.println(canFlowOff(map, 4, 4));
    }
}
