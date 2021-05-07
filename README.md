# SOMPS
A tool generates online monitor for perturbed STL 
# Case

## HVAC Model
We demonstrate the simulation results of the benchmark using a HVAC Simulink model.
![image](https://user-images.githubusercontent.com/43061103/117418417-f99b9e00-af4d-11eb-8288-eb7db6bac754.png)
1. Physical model. In this part, we build the temperature and humidity dynamics which are influenced by outside environment , human, radiators and air conditioning. 

2. Controller. To control vectorial signal, so we use Switch block rather than StateFlow to implement the control logic. User can change this module with ML component as demand.

3. Environment. It is worth noting that Environment is the stochastic part of the entire model including the outdoor environment and people influence on temperature which the number of people is parameterized. Weather datas are    extrapolated to fit our need from the dataset https://www.kaggle.com/selfishgene/historical-hourly-weather-data.  
4. Evaluations. This part is for verification about HVAC system. 

## Experiment
We present three properties relative to thermal comfort.
![image](https://user-images.githubusercontent.com/43061103/117419120-cc9bbb00-af4e-11eb-9296-f955059dfc90.png)
We set Tim to 1 and the property is specified by the formula !φ.
![image](https://user-images.githubusercontent.com/43061103/117419427-21d7cc80-af4f-11eb-89e3-8fad42f72229.png)

With the help of our tool SOMPS, online monitors are constructed as shown in below.
![image](https://user-images.githubusercontent.com/20868538/117424419-5732e900-af54-11eb-8871-fc591a08cd5a.png)
For formula φ, we set Tim to 1 and use a sinusoidal block for the airflow to highlight perturbation more. We get the online monitor structure in Fig. 11(c) and Fig. 11(d).
