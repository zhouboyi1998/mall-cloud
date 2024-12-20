package com.cafe.foundation.feign;

import com.cafe.common.core.feign.FeignRequestInterceptor;
import com.cafe.foundation.model.vo.AreaDetailVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.feign
 * @Author: zhouboyi
 * @Date: 2023/10/27 16:51
 * @Description:
 */
@FeignClient(value = "mall-foundation", contextId = "area", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/area")
public interface AreaFeign {

    /**
     * 根据省份id、城市id、区县id获取区域
     *
     * @param provinceId
     * @param cityId
     * @param districtId
     * @return
     */
    @GetMapping(value = "/detail/{provinceId}/{cityId}/{districtId}")
    ResponseEntity<AreaDetailVO> detail(
        @PathVariable(value = "provinceId") Long provinceId,
        @PathVariable(value = "cityId") Long cityId,
        @PathVariable(value = "districtId") Long districtId
    );
}
