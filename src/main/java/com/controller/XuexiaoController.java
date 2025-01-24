
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 支援学校
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/xuexiao")
public class XuexiaoController {
    private static final Logger logger = LoggerFactory.getLogger(XuexiaoController.class);

    private static final String TABLE_NAME = "xuexiao";

    @Autowired
    private XuexiaoService xuexiaoService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private JianliService jianliService;//简历
    @Autowired
    private LiuyanService liuyanService;//留言板
    @Autowired
    private ToudiService toudiService;//简历投递
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private ZhaopinService zhaopinService;//职位招聘
    @Autowired
    private ZhaopinCollectionService zhaopinCollectionService;//职位收藏
    @Autowired
    private ZhaopinLiuyanService zhaopinLiuyanService;//职位评论
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("支援学校".equals(role))
            params.put("xuexiaoId",request.getSession().getAttribute("userId"));
        params.put("xuexiaoDeleteStart",1);params.put("xuexiaoDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = xuexiaoService.queryPage(params);

        //字典表数据转换
        List<XuexiaoView> list =(List<XuexiaoView>)page.getList();
        for(XuexiaoView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        XuexiaoEntity xuexiao = xuexiaoService.selectById(id);
        if(xuexiao !=null){
            //entity转view
            XuexiaoView view = new XuexiaoView();
            BeanUtils.copyProperties( xuexiao , view );//把实体数据重构到view中
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody XuexiaoEntity xuexiao, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,xuexiao:{}",this.getClass().getName(),xuexiao.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<XuexiaoEntity> queryWrapper = new EntityWrapper<XuexiaoEntity>()
            .eq("username", xuexiao.getUsername())
            .or()
            .eq("xuexiao_phone", xuexiao.getXuexiaoPhone())
            .eq("xuexiao_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XuexiaoEntity xuexiaoEntity = xuexiaoService.selectOne(queryWrapper);
        if(xuexiaoEntity==null){
            xuexiao.setXuexiaoDelete(1);
            xuexiao.setCreateTime(new Date());
            xuexiao.setPassword("123456");
            xuexiaoService.insert(xuexiao);
            return R.ok();
        }else {
            return R.error(511,"账户或者联系方式已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody XuexiaoEntity xuexiao, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,xuexiao:{}",this.getClass().getName(),xuexiao.toString());
        XuexiaoEntity oldXuexiaoEntity = xuexiaoService.selectById(xuexiao.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(xuexiao.getXuexiaoPhoto()) || "null".equals(xuexiao.getXuexiaoPhoto())){
                xuexiao.setXuexiaoPhoto(null);
        }

            xuexiaoService.updateById(xuexiao);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<XuexiaoEntity> oldXuexiaoList =xuexiaoService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<XuexiaoEntity> list = new ArrayList<>();
        for(Integer id:ids){
            XuexiaoEntity xuexiaoEntity = new XuexiaoEntity();
            xuexiaoEntity.setId(id);
            xuexiaoEntity.setXuexiaoDelete(2);
            list.add(xuexiaoEntity);
        }
        if(list != null && list.size() >0){
            xuexiaoService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<XuexiaoEntity> xuexiaoList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            XuexiaoEntity xuexiaoEntity = new XuexiaoEntity();
//                            xuexiaoEntity.setUsername(data.get(0));                    //账户 要改的
//                            //xuexiaoEntity.setPassword("123456");//密码
//                            xuexiaoEntity.setXuexiaoName(data.get(0));                    //支援学校名称 要改的
//                            xuexiaoEntity.setXuexiaoTypes(Integer.valueOf(data.get(0)));   //支援学校类型 要改的
//                            xuexiaoEntity.setXuexiaoPhone(data.get(0));                    //联系方式 要改的
//                            xuexiaoEntity.setXuexiaoEmail(data.get(0));                    //邮箱 要改的
//                            xuexiaoEntity.setXuexiaoPhoto("");//详情和图片
//                            xuexiaoEntity.setXuexiaoContent("");//详情和图片
//                            xuexiaoEntity.setXuexiaoDelete(1);//逻辑删除字段
//                            xuexiaoEntity.setCreateTime(date);//时间
                            xuexiaoList.add(xuexiaoEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //联系方式
                                if(seachFields.containsKey("xuexiaoPhone")){
                                    List<String> xuexiaoPhone = seachFields.get("xuexiaoPhone");
                                    xuexiaoPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> xuexiaoPhone = new ArrayList<>();
                                    xuexiaoPhone.add(data.get(0));//要改的
                                    seachFields.put("xuexiaoPhone",xuexiaoPhone);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<XuexiaoEntity> xuexiaoEntities_username = xuexiaoService.selectList(new EntityWrapper<XuexiaoEntity>().in("username", seachFields.get("username")).eq("xuexiao_delete", 1));
                        if(xuexiaoEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(XuexiaoEntity s:xuexiaoEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //联系方式
                        List<XuexiaoEntity> xuexiaoEntities_xuexiaoPhone = xuexiaoService.selectList(new EntityWrapper<XuexiaoEntity>().in("xuexiao_phone", seachFields.get("xuexiaoPhone")).eq("xuexiao_delete", 1));
                        if(xuexiaoEntities_xuexiaoPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(XuexiaoEntity s:xuexiaoEntities_xuexiaoPhone){
                                repeatFields.add(s.getXuexiaoPhone());
                            }
                            return R.error(511,"数据库的该表中的 [联系方式] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        xuexiaoService.insertBatch(xuexiaoList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }

    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        XuexiaoEntity xuexiao = xuexiaoService.selectOne(new EntityWrapper<XuexiaoEntity>().eq("username", username));
        if(xuexiao==null || !xuexiao.getPassword().equals(password))
            return R.error("账号或密码不正确");
        else if(xuexiao.getXuexiaoDelete() != 1)
            return R.error("账户已被删除");
        String token = tokenService.generateToken(xuexiao.getId(),username, "xuexiao", "支援学校");
        R r = R.ok();
        r.put("token", token);
        r.put("role","支援学校");
        r.put("username",xuexiao.getXuexiaoName());
        r.put("tableName","xuexiao");
        r.put("userId",xuexiao.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody XuexiaoEntity xuexiao, HttpServletRequest request) {
//    	ValidatorUtils.validateEntity(user);
        Wrapper<XuexiaoEntity> queryWrapper = new EntityWrapper<XuexiaoEntity>()
            .eq("username", xuexiao.getUsername())
            .or()
            .eq("xuexiao_phone", xuexiao.getXuexiaoPhone())
            .andNew()
            .eq("xuexiao_delete", 1)
            ;
        XuexiaoEntity xuexiaoEntity = xuexiaoService.selectOne(queryWrapper);
        if(xuexiaoEntity != null)
            return R.error("账户或者联系方式已经被使用");
        xuexiao.setXuexiaoDelete(1);
        xuexiao.setCreateTime(new Date());
        xuexiaoService.insert(xuexiao);

        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id, HttpServletRequest request) {
        XuexiaoEntity xuexiao = xuexiaoService.selectById(id);
        xuexiao.setPassword("123456");
        xuexiaoService.updateById(xuexiao);
        return R.ok();
    }

	/**
	 * 修改密码
	 */
	@GetMapping(value = "/updatePassword")
	public R updatePassword(String  oldPassword, String  newPassword, HttpServletRequest request) {
        XuexiaoEntity xuexiao = xuexiaoService.selectById((Integer)request.getSession().getAttribute("userId"));
		if(newPassword == null){
			return R.error("新密码不能为空") ;
		}
		if(!oldPassword.equals(xuexiao.getPassword())){
			return R.error("原密码输入错误");
		}
		if(newPassword.equals(xuexiao.getPassword())){
			return R.error("新密码不能和原密码一致") ;
		}
        xuexiao.setPassword(newPassword);
		xuexiaoService.updateById(xuexiao);
		return R.ok();
	}



    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        XuexiaoEntity xuexiao = xuexiaoService.selectOne(new EntityWrapper<XuexiaoEntity>().eq("username", username));
        if(xuexiao!=null){
            xuexiao.setPassword("123456");
            xuexiaoService.updateById(xuexiao);
            return R.ok();
        }else{
           return R.error("账号不存在");
        }
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrXuexiao(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        XuexiaoEntity xuexiao = xuexiaoService.selectById(id);
        if(xuexiao !=null){
            //entity转view
            XuexiaoView view = new XuexiaoView();
            BeanUtils.copyProperties( xuexiao , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }



    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = xuexiaoService.queryPage(params);

        //字典表数据转换
        List<XuexiaoView> list =(List<XuexiaoView>)page.getList();
        for(XuexiaoView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        XuexiaoEntity xuexiao = xuexiaoService.selectById(id);
            if(xuexiao !=null){


                //entity转view
                XuexiaoView view = new XuexiaoView();
                BeanUtils.copyProperties( xuexiao , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody XuexiaoEntity xuexiao, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,xuexiao:{}",this.getClass().getName(),xuexiao.toString());
        Wrapper<XuexiaoEntity> queryWrapper = new EntityWrapper<XuexiaoEntity>()
            .eq("username", xuexiao.getUsername())
            .or()
            .eq("xuexiao_phone", xuexiao.getXuexiaoPhone())
            .andNew()
            .eq("xuexiao_delete", 1)
//            .notIn("xuexiao_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XuexiaoEntity xuexiaoEntity = xuexiaoService.selectOne(queryWrapper);
        if(xuexiaoEntity==null){
            xuexiao.setXuexiaoDelete(1);
            xuexiao.setCreateTime(new Date());
            xuexiao.setPassword("123456");
        xuexiaoService.insert(xuexiao);

            return R.ok();
        }else {
            return R.error(511,"账户或者联系方式已经被使用");
        }
    }

}

