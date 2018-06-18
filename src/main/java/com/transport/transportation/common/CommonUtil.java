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

    public EcomRequestCustom copyEcomRequest(EcommerceTaxiRequest transReq) {
        EcomRequestCustom dest = new EcomRequestCustom();

        BeanUtils.copyProperties(transReq, dest);

        if (transReq.getDest() != null) {
            dest.setDestination(transReq.getDest().getDestinationname());
        }
        if (transReq.getSour() != null) {
            dest.setSource(transReq.getSour().getSourcename());
        }

        Ecommerce ecommerce = transReq.getEcommerce();
        dest.setProductid(ecommerce.getProductid());
        dest.setProductname(ecommerce.getProductname());
        dest.setProductprice(ecommerce.getPrice());



        SignUp user = transReq.getUser();

        return dest;
    }

    public RideHistory getCustomerHistory(TransportRequest transReq, String type) {
        RideHistory dest = new RideHistory();

        BeanUtils.copyProperties(transReq, dest);

        dest.setDestination(transReq.getDest().getDestinationname());
        dest.setSource(transReq.getSour().getSourcename());
        dest.setTraveltype(type);

        return dest;
    }

    public RideHistory getCustomerHistory(TransitRequest transReq, String type) {
        RideHistory dest = new RideHistory();

        BeanUtils.copyProperties(transReq, dest);

        dest.setRequestid(transReq.getRequestId());
        dest.setDestination(transReq.getDest().getDestinationname());
        dest.setSource(transReq.getSour().getSourcename());
        dest.setTraveltype(type);

        return dest;
    }

    public RideHistory getCustomerHistory(EcommerceTaxiRequest transReq, String type) {
        RideHistory dest = new RideHistory();

        BeanUtils.copyProperties(transReq, dest);

        dest.setRequestid(transReq.getRequestid());

        if (transReq.getDest() != null) {
            dest.setDestination(transReq.getDest().getDestinationname());
        }

        if (transReq.getSour() != null) {
            dest.setSource(transReq.getSour().getSourcename());
        }

        Ecommerce ecommerce = transReq.getEcommerce();
        dest.setProductid(ecommerce.getProductid());
        dest.setProductname(ecommerce.getProductname());
        dest.setProductprice(ecommerce.getPrice());

        dest.setTraveltype(type);

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
