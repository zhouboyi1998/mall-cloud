package com.cafe.system.feign;

import com.cafe.common.core.feign.FeignRequestInterceptor;
import com.cafe.system.dto.AreaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.system.feign
 * @Author: zhouboyi
 * @Date: 2023/10/27 16:51
 * @Description:
 */
@FeignClient(value = "mall-system", configuration = {FeignRequestInterceptor.class})
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
    @GetMapping(value = "/dto/{provinceId}/{cityId}/{districtId}")
    ResponseEntity<AreaDTO> dto(
        @PathVariable(value = "provinceId") Long provinceId,
        @PathVariable(value = "cityId") Long cityId,
        @PathVariable(value = "districtId") Long districtId
    );
}
