
#!/usr/bin/env python
import socket


TCP_IP = 'ukhas.net'
TCP_PORT = 3010
BUFFER_SIZE = 1024

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((TCP_IP, TCP_PORT))

while 1:
	print s.recv(BUFFER_SIZE)

s.close()
