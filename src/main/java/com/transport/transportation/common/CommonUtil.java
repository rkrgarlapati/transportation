package com.transport.transportation.common;

import com.transport.transportation.dto.RideHistory;
import com.transport.transportation.entity.*;
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

    public RideHistory getCustomerHistory(TransportRequest transReq) {
        RideHistory dest = new RideHistory();

        BeanUtils.copyProperties(transReq, dest);

        dest.setDestination(transReq.getDest().getDestinationname());
        dest.setSource(transReq.getSour().getSourcename());

        return dest;
    }
    public RideHistory getCustomerHistory(TransitRequest transReq) {
        RideHistory dest = new RideHistory();

        BeanUtils.copyProperties(transReq, dest);

        dest.setDestination(transReq.getDest().getDestinationname());
        dest.setSource(transReq.getSour().getSourcename());

        return dest;
    }

    public TransitRequestCustom copyTransitRequest(TransitRequest transReq) {
        TransitRequestCustom dest = new TransitRequestCustom();

        BeanUtils.copyProperties(transReq, dest);

        dest.setDestination(transReq.getDest().getDestinationname());
        dest.setSource(transReq.getSour().getSourcename());

        if (transReq.getServi() != null) {
            dest.setService(transReq.getServi().getServicename());
        }
        if (transReq.getProd() != null) {
            dest.setProduct(transReq.getProd().getProductname());
        }
        dest.setSourceId(null);
        dest.setDestinationId(null);
        dest.setServiceId(null);
        dest.setProductId(null);

        SignUp user = transReq.getUser();

        return dest;
    }
}
