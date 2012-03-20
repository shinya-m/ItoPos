/*
  felicalib - FeliCa access wrapper library

  Copyright (c) 2007, Takuya Murakami, All rights reserved.

  Redistribution and use in source and binary forms, with or without
  modification, are permitted provided that the following conditions are
  met:

  1. Redistributions of source code must retain the above copyright notice,
  this list of conditions and the following disclaimer. 

  2. Redistributions in binary form must reproduce the above copyright
  notice, this list of conditions and the following disclaimer in the
  documentation and/or other materials provided with the distribution. 

  3. Neither the name of the project nor the names of its contributors
  may be used to endorse or promote products derived from this software
  without specific prior written permission. 

  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
  "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
  LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
  A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
/**
   @file dump.c

   FeliCa ƒ_ƒ“ƒv
*/

#include <stdio.h>
#include <winsock2.h>
#include <string.h>

#include "felicalib.h"
#pragma     comment(lib,"ws2_32.lib")
#pragma     comment(lib,"felicaLib")

typedef struct{
	char dstaddr[32];
	int port;
	int ptime;
}lowsan_felica_config_t;

lowsan_felica_config_t lowsan_felica_cfg;

static void printserviceinfo(uint16 s);
static void hexdump(uint8 *addr, int n);

void read_config(char *fname)
{
	char strbuf[256];
	FILE *fp;
	char key[16];
	char val[16];
	char equal[8];

	memset(&lowsan_felica_cfg, 0, sizeof(lowsan_felica_cfg));

	if((fp = fopen(fname, "r")) == NULL){
		printf("file not open");
		exit(2);
	}

	while( (fgets(strbuf, sizeof(strbuf), fp)) != NULL ){
		if(strbuf[0] == '#')
			continue;

		if(sscanf(strbuf, "%s%s%s", key, equal, val) == 3){
			if(!strcmp(key, "dst_ip")){
				strncpy(lowsan_felica_cfg.dstaddr, val, sizeof(val));
			}
			else if(!strcmp(key, "dst_port")){
				lowsan_felica_cfg.port = atoi(val);
			}
			else if(!strcmp(key, "polling_time")){
				lowsan_felica_cfg.ptime = atoi(val);
			}
			memset(val, 0, sizeof(val));
		}
	}

	printf("addr:%s port:%d \n", lowsan_felica_cfg.dstaddr, lowsan_felica_cfg.port);
	fclose(fp);

	//debug
	//print_cfg();
}



/**/
void transData(felica *p, int n, char *rtn)
{
    int i;
	char tmp[32];
	char buf[32];

	memset(buf, 0, sizeof(buf));
	for(i=0; i<n; i++){
		sprintf(tmp, "%02x", p->IDm[i]);
		strncat(buf, tmp, sizeof(uint8)*2);
	}
	strcpy(rtn, buf);
}

void transID(felica *p)
{
	WSADATA wsaData;
	SOCKET sock;
	struct sockaddr_in addr;
	char trIDm[32];

	transData(p, 8, trIDm);
	WSAStartup(MAKEWORD(2,0), &wsaData);
	sock = socket(AF_INET, SOCK_DGRAM, 0);
	addr.sin_family = AF_INET;
	addr.sin_port = htons(lowsan_felica_cfg.port);
	addr.sin_addr.S_un.S_addr = inet_addr(lowsan_felica_cfg.dstaddr);
	//sendto(sock, trIDm, sizeof(trIDm), 0, (struct sockaddr *)&addr, sizeof(addr));
	printf("trIDm: %s\n", trIDm);
	sendto(sock, trIDm, 16, 0, (struct sockaddr *)&addr, sizeof(addr));
	closesocket(sock);
	WSACleanup();

}


int _tmain(int argc, _TCHAR *argv[])
{
    pasori *p;
    felica *f, *f2;
    int i, j, k;


	read_config("LowsanFelica.ini");

	while(1){
		Sleep(1000);
		p = pasori_open(NULL);
		if (!p) {
			continue;
			//exit(1);
		}
		pasori_init(p);
	    
		f = felica_polling(p, POLLING_ANY, 0, 0);
		if (!f) {
			felica_free(f);
			pasori_close(p);
			continue;
		}

		transID(f);
		felica_free(f);

		f = felica_enum_systemcode(p);
		if (!f) {
			//exit(1);
			continue;
		}

		for (i = 0; i < f->num_system_code; i++) {

			f2 = felica_enum_service(p, N2HS(f->system_code[i]));
			if (!f2) {

				continue;
				//exit(1);
			}

			for (j = 0; j < f2->num_area_code; j++) {
			}            

			for (j = 0; j < f2->num_service_code; j++) {
				uint16 service = f2->service_code[j];
				//printserviceinfo(service);

				for (k = 0; k < 255; k++) {
					uint8 data[16];

					if (felica_read_without_encryption02(f2, service, 0, (uint8)k, data)) {
						break;
					}

				}
			}
		}
		pasori_close(p);
	}
	felica_free(f2);
	felica_free(f);
	pasori_close(p);

    return 0;
}

static void printserviceinfo(uint16 s)
{
    char *ident;

    switch ((s >> 1) & 0xf) {
    case 0: ident = "Area Code"; break;
    case 4: ident = "Random Access R/W"; break; 
    case 5: ident = "Random Access Read only"; break; 
    case 6: ident = "Cyclic Access R/W"; break; 
    case 7: ident = "Cyclic Access Read only"; break; 
    case 8: ident = "Purse (Direct)"; break;
    case 9: ident = "Purse (Cashback/decrement)"; break;
    case 10: ident = "Purse (Decrement)"; break;
    case 11: ident = "Purse (Read only)"; break;
    default: ident = "INVALID or UNKOWN"; break;
    }

    printf("# Serivce code = %04X : %s", s, ident);
    if ((s & 0x1) == 0) {
        printf(" (Protected)");
    }
    printf("\n");
}

static void hexdump(uint8 *addr, int n)
{
    int i;
    for (i = 0; i < n; i++) {
        printf("%02X ", addr[i]);
    }
}
