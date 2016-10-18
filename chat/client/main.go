package main

import (
	"bufio"
	"fmt"
	"net"
	"os"
)

var (

)

func main() {
	if len(os.Args) < 2 {
		fmt.Println("need user name")
		os.Exit(0)
	}
	userName := os.Args[1]

	conn, err := net.Dial("tcp", ":8000")
	if err != nil {
		fmt.Printf("net.Dial: %s\n", err)
		os.Exit(1)
	}


	go func() {
		input := bufio.NewScanner(conn)
		for input.Scan() {
			fmt.Println(input.Text())
		}
		if input.Err() != nil {
			fmt.Printf("scanner for connection %s\n", input.Err())
		}
	}()


	fmt.Fprintln(conn, userName)

	input := bufio.NewScanner(os.Stdin)

	for input.Scan() {
		b := input.Bytes()
		b = append(b, '\n')
		_, err := conn.Write(b)
		if err != nil {
			fmt.Printf("net.Conn.Write %s\n", err)
			break
		}
	}

	if input.Err() != nil {
		fmt.Printf("scanner for os.Stdin: %s\n", input.Err())
	}
}
