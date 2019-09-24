package com.frame.tool;

import java.util.UUID;

/**获取32位的uuid
 * @author zhuzheng
 *
 */
public class UUIDUtil {

	static public String createUUID()
	{
		return UUID.randomUUID().toString().replace("-", "");
	}

}
