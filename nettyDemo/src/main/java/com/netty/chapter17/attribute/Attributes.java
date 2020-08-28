package com.netty.chapter17.attribute;

import io.netty.util.AttributeKey;
import com.netty.chapter17.session.Session;

public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
