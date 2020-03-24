package ai.dongsheng.controller;


import ai.dongsheng.model.vo.OutputVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户收藏列表 前端控制器
 * </p>
 *
 * @author MichelleJou
 * @since 2019-12-06
 */
@RestController
@RequestMapping("/favorite")
public class FavoriteController {
        @GetMapping("/test")
        public OutputVo test(){
            return new OutputVo("你好啊！");
        }

}
