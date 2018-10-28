# Solving IVP problem by Numerical methods

---

Documentation of Programming Assigment

$$\begin{cases} y' = -y-x \\ y(0)= 1 \end{cases}$$

# Content

---

1. Exact Solution
2. Structure of Code
    1. Class Diagram
    2. Exact Solution
    3. Euler method
    4. Improved Euler
    5. Runge-Kutta
3. Sostb

# Exact Solution

---

$$y' = - y - x$$

$$\text{ Use Variation of variable method: }$$

$$\text Suppose \quad y = uv: \quad  u'v+uv'+uv=-x$$

$$\begin{cases} uv'+uv =0 \\ u'v=-x  \end{cases}$$

$$v' = v$$

$$u'v=-x$$

$$\frac{dv}{v} = -dx$$

$$\int \frac{\mathrm{d}v}{v} = -\int \mathrm{d}x$$

$$u' = -x/v$$

$$du = -\int{xc_1e^{x}}\mathrm{d}x$$

$$ln(v) = -x + c_1  $$

$$v = c_1e^{-x}$$

$$u = (-e^x(x-1)+c_2)\frac{1}{c_1}$$

$$\frac{y}{v}= (-e^x(x-1)+c_2)\frac{1}{c_1}$$

$$y = e^{-x}(-e^x(x-1)+c_2)$$

$$y = -x+c_2e^{-x}+1$$

$$\text IVP \quad y(0)=1 : \quad 1 = 1+c_2 \implies c_2 = 0$$

$$y = -x + 1$$

# Structure of Code

---

## Class Diagram

---

![](DefEq_PA_uml-d21fd502-6fd7-4122-abcc-f935b41ec86b.png)

## Exact

---

In this class we create data set of Exact solution of this problem.

$$c = \frac{y_0+x_0-1}{e^{-x_0}}$$

$$y = -x + 1 + ce^{-x}$$

![](Untitled-f612343b-c104-40e6-b038-75dd2a936c2c.png)

## Euler

---

In this class we create data set of Euler method.

$$\text{Step:} \quad h = \frac{X-x_0}{N}$$

$$x_i=x_0+ih$$

$$y_{i+1}=y_i+h*(-y_i-x_i)$$

![](Untitled-24b71005-2e5d-43d8-bd95-64e4147e6bfa.png)

## Improved Euler

---

Class of Improved Euler method.

Inherited from Euler. Override only method where we fine y_(i+1) and constructor.

$$f(x,y)=y'=-y-x$$

$$y_{i+1}=y_i+h\frac{f(x_i,y_i)+f(x_i+h, y_i+hf(x_i,y_i))}{2}$$

![](Untitled-6ba20bb8-c6a9-40b9-b21b-9083fcec3193.png)

## Runge-Kutta

---

Class for solution by Runge-Kutta numerical method

Inherited from Euler. Override only method where we fine y_(i+1) and counctructor.

$$k_1 = hf(x_i, y_i)$$

$$k_2 = hf(x_i+\frac{h}{2}, y_i+ \frac{k1}{2})$$

$$k_3 = hf(x_i+\frac{h}{2}, y_i+ \frac{k2}{2})$$

$$k_4 = hf(x_i+h, y_i+ k_3)$$

$$y_{i+1} = y_i + \frac{k_1}{6} + \frac{k_2}{3} + \frac{k_3}{3} + \frac{k_4}{6} + O(h^5) $$

![](Untitled-a743a173-0edf-4072-8b45-5d544a21a6c9.png)