package werson.leetcode;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 考生就坐
 * @author werson
 * @version 1.0.0
 * @since 2018/9/4
 */
public class ExamRoom {

	public static List<String> seatList;

	public ExamRoom(int N) {
		seatList = Lists.newArrayListWithCapacity(N);
		for(int i = 0; i < N; i++){
			seatList.add(i, "");
		}
	}

	public int seat() {
		int maxDistanceIndex = -1;
		int maxDis = 0;
		for(int i = 0; i < seatList.size(); i++){
			if(seatList.get(i).equals("")){
				if(maxDistanceIndex == -1) maxDistanceIndex = i;
				int thisDistance = maxDistance(i);
				if(thisDistance > maxDis){
					maxDistanceIndex = i;
					maxDis = thisDistance;
				}
			}
		}
		seatList.set(maxDistanceIndex, "done");
		return maxDistanceIndex;
	}

	public void leave(int p) {
		seatList.set(p, "");
	}

	public static void main(String[] args) {
		ExamRoom examRoom = new ExamRoom(8);
		System.out.println(examRoom.seat());
		System.out.println(examRoom.seat());
		System.out.println(examRoom.seat());
		examRoom.leave(0);
		examRoom.leave(7);
		System.out.println(examRoom.seat());
		System.out.println(examRoom.seat());
		System.out.println(examRoom.seat());
		System.out.println(examRoom.seat());
		System.out.println(examRoom.seat());
		System.out.println(examRoom.seat());
	}

	public static int maxDistance(int index){
		int leftDistance = 0;
		int leftIndex = index - 1;
		if(leftIndex > -1){
			while (seatList.get(leftIndex).equals("")){
				leftDistance++;
				leftIndex--;
				if(leftIndex < 0) break;
			}
		}else{
			leftDistance = -1;
		}

		int rightDistance = 0;
		int rightIndex = index + 1;
		if(rightIndex < seatList.size()){
			while (seatList.get(rightIndex).equals("")){
				rightDistance++;
				rightIndex++;
				if(rightIndex == seatList.size()) break;
			}
		}else{
			rightDistance = -1;
		}
		if(leftDistance == -1 && rightDistance == -1) return 0;
		if(leftDistance == -1) return rightDistance;
		if(rightDistance == -1) return leftDistance;
		return Math.min(leftDistance, rightDistance);
	}

}
