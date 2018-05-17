package com.transport.transportation.common;

import com.transport.transportation.entity.SignUp;
import com.transport.transportation.entity.TransRequestCustom;
import com.transport.transportation.entity.TransportRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

    public TransRequestCustom copyRequest(TransportRequest transReq) {
        TransRequestCustom dest = new TransRequestCustom();

        BeanUtils.copyProperties(transReq, dest);

        dest.setDestination(transReq.getDest().getDestinationname());
        dest.setSource(transReq.getSour().getSourcename());
        dest.setSourceId(null);
        dest.setDestinationId(null);
        SignUp user = transReq.getUser();

        return dest;
    }
}
