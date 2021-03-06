package com.backend.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
   /* @Autowired(required = false)
    private Cloud cloud;

    private Environment springEnvironment;

    @Autowired
    public InfoController(Environment springEnvironment) {
        this.springEnvironment = springEnvironment;
    }

    @ResponseBody
    @RequestMapping(value = "/info")
    public ApplicationInfo info() {
        return new ApplicationInfo(springEnvironment.getActiveProfiles(), getServiceNames());
    }

    @RequestMapping(value = "/env")
    @ResponseBody
    public Map<String, String> showEnvironment() {
        return System.getenv();
    }

    @RequestMapping(value = "/service")
    @ResponseBody
    public List<ServiceInfo> showServiceInfo() {
        if (cloud != null) {
            return cloud.getServiceInfos();
        } else {
            return new ArrayList<ServiceInfo>();
        }
    }

    private String[] getServiceNames() {
        if (cloud != null) {
            final List<ServiceInfo> serviceInfos = cloud.getServiceInfos();

            List<String> names = new ArrayList<String>();
            for (ServiceInfo serviceInfo : serviceInfos) {
                names.add(serviceInfo.getId());
            }
            return names.toArray(new String[names.size()]);
        } else {
            return new String[]{};
        }
    }*/
}
