package com.jem.ecdc;

public class Vector {

	public float x;
	public float y;
	public float z;
	
	public Vector() {
		this(0, 0, 0);
	}
	
	public Vector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public String toString() {
		return String.format("%.2f %.2f %.2f", x, y, z);
	}
	
}
