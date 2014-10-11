###Maker+ API
====
 
* ###事件


	1. 获取事件列表

		```
			GET Http://ip/makerPlus/servlet/Event
	
			Results:
			[事件列表Array]
		
		```

	2. 获取事件参数列表

		```
			GET Http://ip/makerPlus/servlet/Event?EventName=<事件名>
			
			Results:
			[参数列表Array]
		```

	3. 发送普通事件参数信息

		```
			POST Http://ip/makerPlus/servlet/Event?EventName=<事件名>
			
			Params:
			{data:{事件参数1:值1，事件参数2:值2，...}
			
			Returns:
			null
			
		```

	4. 发送注册事件参数信息
	
		```
			POST Http://ip/makerPlus/servlet/Event?EventName=RegisterEvent
			
			Params:
			{data:{事件参数1:值1，事件参数2:值2，...}
			
			Returns:
			会员生成id号
			
		```
	5. 发送签到事件参数信息
		```
			GET Http://ip/makerPlus/servlet/Event?EventName=CheckEvent
			
			Params:
			{data:{id:会员id号}}
			
			Returns:
			{会员信息json}
			/*e.g.
			{ "_id" : { "$oid" : "5436019596bcae3914718228"} , "班号" : "机研132" , "电话" : "158******" , "学号" : "2013****" , "性别" : "男" , "姓名" : "zcw" , "电子邮箱" : "zcwist@gmail.com" , "院系" : "机械"}
			*/

* ###查询

	1. 获取当日签到名单
		
		```
		GET Http://ip/makerPlus/servlet/Query?What=SignInList
		
		Returns:
		[{"name":"demo","phoneNum":"152****0910"},  ...]
		```



	
	