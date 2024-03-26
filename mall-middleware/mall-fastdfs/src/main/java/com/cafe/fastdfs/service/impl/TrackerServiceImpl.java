package com.cafe.fastdfs.service.impl;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.fastdfs.service.TrackerService;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.fastdfs.service.impl
 * @Author: zhouboyi
 * @Date: 2024/3/26 16:46
 * @Description:
 */
@Service
public class TrackerServiceImpl implements TrackerService {

    @Override
    public String url() throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        String ip = trackerServer.getInetSocketAddress().getHostString();
        Integer trackerPort = ClientGlobal.getG_tracker_http_port();
        return ip + StringConstant.COLON + trackerPort;
    }
}
