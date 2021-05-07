# SOMPS
A tool generates online monitor for perturbed STL 
# Case Study
We demonstrate the simulation results of the benchmark using a HVAC Simulink model.
![image](https://user-images.githubusercontent.com/43061103/117417410-e63c0300-af4c-11eb-8809-6d9bb0263202.png)

1. Physical model. In this part, we build the temperature and humidity dynamics which are influenced by outside environment , human, radiators and air conditioning. 

2. Controller. To control vectorial signal, so we use Switch block rather than StateFlow to implement the control logic. User can change this module with ML component as demand.

3. Environment. It is worth noting that Environment is the stochastic part of the entire model including the outdoor environment and people influence on temperature which the number of people is parameterized. Weather datas are    extrapolated to fit our need from the dataset https://www.kaggle.com/selfishgene/historical-hourly-weather-data.  
4. Evaluations. This part is for verification about HVAC system. 
