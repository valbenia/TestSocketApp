package com.bkspeed.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.bkspeed.model.Room;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseUtils {
	
	public static ObjectMapper obj = new ObjectMapper();
	public static ExecutorService executor = Executors.newFixedThreadPool(BaseConstant.NUM_OF_THREAD);
	public static List<Integer> listPort = Arrays.asList(2222);
	public static List<Room> listRoom = new ArrayList<Room>();
}
