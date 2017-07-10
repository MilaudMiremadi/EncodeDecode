package com.jem.ecdc;

public class Matrix {

	public float a, b, c;
	public float d, e, f;
	public float g, h, i;

	public Matrix() {
		this.a = 1;
		this.b = 0;
		this.c = 0;
		this.d = 0;
		this.e = 1;
		this.f = 0;
		this.g = 0;
		this.h = 0;
		this.i = 1;
	}

	public Matrix(float a, float b, float c, float d, float e, float f, float g, float h, float i) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.g = g;
		this.h = h;
		this.i = i;
	}

	public Vector multiply(Vector v) {
		float vx = v.x;
		float vy = v.y;
		float vz = v.z;
		float newX = (a * vx) + (d * vy) + (g * vz);
		float newY = (b * vx) + (e * vy) + (h * vz);
		float newZ = (c * vx) + (f * vy) + (i * vz);

		return new Vector(newX, newY, newZ);
	}

	public void multiply(float scalar) {
		this.a *= scalar;
		this.b *= scalar;
		this.c *= scalar;
		this.d *= scalar;
		this.e *= scalar;
		this.f *= scalar;
		this.g *= scalar;
		this.h *= scalar;
		this.i *= scalar;
	}

	public void multiply(Matrix mat) {
		float na = a * mat.a + d * mat.b + g * mat.c;
		float nb = b * mat.a + e * mat.b + h * mat.c;
		float nc = c * mat.a + f * mat.b + i * mat.c;

		float nd = a * mat.d + d * mat.e + g * mat.f;
		float ne = b * mat.d + e * mat.e + h * mat.f;
		float nf = c * mat.d + f * mat.e + i * mat.f;

		float ng = a * mat.g + d * mat.h + g * mat.i;
		float nh = b * mat.g + e * mat.h + h * mat.i;
		float ni = c * mat.g + f * mat.h + i * mat.i;

		a = na;
		b = nb;
		c = nc;

		d = nd;
		e = ne;
		f = nf;

		g = ng;
		h = nh;
		i = ni;
	}

	public Matrix inverse() {
		float det = (g * b * f - g * c * e - d * b * i + d * c * h + a * e * i - a * f * h);
		float na = (e * i - f * h) / det;
		float nb = -(b * i - c * h) / det;
		float nc = (b * f - c * e) / det;
		float nd = -(-g * f + d * i) / det;
		float ne = (-g * c + a * i) / det;
		float nf = -(-d * c + a * f) / det;
		float ng = (-g * e + d * h) / det;
		float nh = -(-g * b + a * h) / det;
		float ni = (-d * b + a * e) / det;

		return new Matrix(na, nb, nc, nd, ne, nf, ng, nh, ni);
	}

	@Override
	public String toString() {
		return a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + g + " " + h + " " + i;
	}

}
