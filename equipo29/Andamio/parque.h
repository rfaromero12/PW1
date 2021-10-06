/*
 * parque.h
 *
 *  Created on: Dec 7, 2020
 *      Author: rfaromero12
 */

#ifndef PARQUE_H_
#define PARQUE_H_
#include <iostream>
#include <list>
#include <string>

class parque {
private:
	string nombreparque_;
	float superficie_;
	float ubicacion_;
	string localizacion;
	list <string> premios_;
	vector <Sendero> senderos_;
	vector <Ruta> rutas_;
public:
	parque();
	virtual ~parque();
};

#endif /* PARQUE_H_ */
