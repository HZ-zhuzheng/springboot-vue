package com.frame.count.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.along.entity.Count;
import com.frame.count.service.CountService;
import com.frame.tool.UUIDUtil;

@Controller
@RequestMapping(value="count")
public class CountController {
	
	@Autowired
	private CountService CountService;
	
	
	/**访问入口
	 * @return
	 */
	@RequestMapping("/")
    public String index(){
//        return "forward:/index.html";
		return "index";
    }
	
	/**
	 * 添加一条用户信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="addCountCon" ,method=RequestMethod.POST)
	public String addCountCon(@RequestBody Count count) {
		return JSON.toJSONString(this.CountService.addCountSer(count));
	}

	/**
	 * 查询用户信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="selectCountCon" ,method=RequestMethod.POST)
	public String selectCountCon(@RequestBody Count count) {
//		System.out.println(JSON.toJSONString(count));
		return JSON.toJSONString(this.CountService.selectCountSer(count));
	}
	
	/**
	 * 查询用户信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="selectCountTotalCon" ,method=RequestMethod.POST)
	public String selectCountTotalCon(@RequestBody Count count) {
		return JSON.toJSONString(this.CountService.selectCountTotalSer(count));
	}
	
	/**
	 * 修改用户信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="updateCountCon" ,method=RequestMethod.POST)
	public String updateCountCon(@RequestBody Count count) {
		System.out.println(JSON.toJSONString(count));
		if (count.getId() == null ||count.getId().equals("")) {
			return "id不能为空";
		}
		if (this.CountService.updateCountSer(count) == 0) {
			return "修改失败";
		}
		return "修改成功";
	}
	
	
	/**
	 * 删除用户信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="deleteCountCon" ,method=RequestMethod.POST)
	public String deleteCountCon(@RequestBody Count count) {
		System.out.println(JSON.toJSONString(count));
		if (count.getDeleteID().length == 0  ) {
			return "id不能为空";
		}
		if (this.CountService.deleteCountSer(count) == 1) {
			return "删除成功";
		}
		return "删除失败";
		
	}
}
