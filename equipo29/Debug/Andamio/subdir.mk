################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../Andamio/el_hijo_del_andamio.cpp 

OBJS += \
./Andamio/el_hijo_del_andamio.o 

CPP_DEPS += \
./Andamio/el_hijo_del_andamio.d 


# Each subdirectory must supply rules for building sources it contributes
Andamio/%.o: ../Andamio/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


