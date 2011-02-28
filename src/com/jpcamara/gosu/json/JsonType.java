package com.jpcamara.gosu.json;

import gw.lang.reflect.DefaultArrayType;
import gw.lang.reflect.IType;
import gw.lang.reflect.ITypeInfo;
import gw.lang.reflect.ITypeLoader;
import gw.lang.reflect.TypeBase;
import gw.lang.reflect.java.IJavaType;
import gw.util.concurrent.LazyVar;

import java.util.Collections;
import java.util.List;

public class JsonType extends TypeBase {

	private static final long serialVersionUID = -8034222055932240161L;

	private ITypeLoader loader;
	private JsonName name;
	private String path;
	private DefaultArrayType arrayType;
	private LazyVar<JsonTypeInfo> typeInfo;

	public JsonType(JsonName name, String path, ITypeLoader typeloader,
			final Json object) {
		this.name = name;
		this.path = path;
		this.loader = typeloader;
		this.arrayType = new DefaultArrayType(this, Json.class, loader);
		this.typeInfo = new LazyVar<JsonTypeInfo>() {
			@Override
			protected JsonTypeInfo init() {
				return new JsonTypeInfo(JsonType.this, object);
			}
		};
	}

	@Override
	public String getName() {
		return path + "." + name.getName();
	}

	@Override
	public String getNamespace() {
		return path;
	}

	@Override
	public String getRelativeName() {
		return name.getName();
	}
	
	public String getJsonRelativeName() {
		return name.getJsonName();
	}

	@Override
	public ITypeInfo getTypeInfo() {
		return typeInfo.get();
	}

	@Override
	public ITypeLoader getTypeLoader() {
		return loader;
	}

	@Override
	public List<? extends IType> getInterfaces() {
		return Collections.emptyList();
	}

	@Override
	public IType getSupertype() {
		return IJavaType.OBJECT;
	}
	
//	@Override
//	public IType getArrayType() {
//		return arrayType;
//	}
//	
//	@Override
//	public Object makeArrayInstance(int length) {
//		return new Json[length];
//	}
	
	public String toString() {
		return getName() + " " + typeInfo.get();
	}
}
