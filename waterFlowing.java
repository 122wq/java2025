public class waterFlowing
{
    public waterFlowing()
    {

    }

    public static boolean canFlowOff(int[][] map, int row, int col)
    {
        
        if (row > 0 || col > 0 || row < map[0].length - 1 || col < map.legnth - 1)
        {
            if (map[row - 1][col] < current && canFlowOff(map, row - 1, col)) {
       	 	return true;
    		}

    		// DOWN
    		if (map[row + 1][col] < current &&
        		canFlowOff(map, row + 1, col)) {
        		return true;
    		}

    		// LEFT
    		if (map[row][col - 1] < current &&
        		canFlowOff(map, row, col - 1)) {
        		return true;
    		}

    		// RIGHT
    		if (map[row][col + 1] < current &&
        		canFlowOff(map, row, col + 1)) {
	        	return true;
	    	}
			return false;
        }
        return true;
    }
    public static void main(String[] args) {
        int[][] map = {
			{100, 99, 200, 200, 200, 200, 200, 200, 200, 200}, 
			{200, 98, 200, 200, 200, 200, 200, 200, 200, 200},
			{200, 97, 96, 200, 200, 200, 200, 200, 200, 200},
			{200, 200, 95, 200, 200, 200, 85, 84, 83, 200},
			{200, 200, 94, 93, 92, 200, 86, 200, 82, 200},
			{200, 150, 200, 200, 91, 200, 87, 200, 81, 200},
			{200, 200, 200, 200, 90, 89, 88, 200, 80, 200},
			{200, 150, 100, 200, 200, 200, 200, 200, 79, 200},
			{200, 200, 200, 200, 200, 200, 200, 200, 78, 77},
			{200, 98, 200, 200, 200, 200, 200, 200, 200, 76}		
	    };
        System.out.println(canFlowOff(map, 3, 2));
    }
}
