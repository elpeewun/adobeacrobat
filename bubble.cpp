#include<iostream>
#include<chrono>
#include <omp.h>
#include<stdlib.h>

using namespace std;
using namespace std::chrono;

void gen(int a[],int b[],int n)
{
	cout<<"Unsorted array is: ";	
	for(int i=0;i<n;i++)
	{

		a[i]=b[i]=n-i;
		cout<<a[i]<<" ";

	}


}


void serial(int a[],int n)
{

	

	time_point<system_clock> start,end;
	
	start=system_clock::now();


	for(int i=0;i<n-1;i++)
	{
		
		for(int j=0;j<n-1;j++)
		{


			if(a[j]>a[j+1])
			{

				int temp=a[j];
				a[j]=a[j+1];
				a[j+1]=temp;
				
			}
		}
	}
cout<<endl<<"sorted array is: ";
cout<<endl;
	for(int i =0; i<n;i++)
	cout<<a[i]<<" ";
	cout<<endl;
	end=system_clock::now();
	
	duration<double> time=end-start;

	cout<<"The serial time is "<<time.count()*1000<<endl;
	
}


void parallel (int a[],int n)
{

	time_point<system_clock> start,end;
	
	start=system_clock::now();

	omp_set_num_threads(2);


	int first=0;
	for(int i=0;i<n;i++)

	{

		first=i%2;
		
		#pragma omp parallel for default(none),shared(a,first,n)
		
		for(int j=first;j<n-1;j=j+2)
		{


			if(a[j]>a[j+1])
			{

				int temp=a[j];
				a[j]=a[j+1];
				a[j+1]=temp;
				
			}
		}

	
	}
cout<<"sorted array is: "<<endl;
	for(int i =0; i<n;i++)
	cout<<a[i]<<" ";
cout<<endl;
	end=system_clock::now();
	
	duration<double> time=end-start;

	cout<<"The parallel time is "<<time.count()*1000<<endl;

	


}

int main()
{

	cout<<"Enter the size"<<endl;
	int n;
	cin>>n;

	int a[n];
	int b[n];

	gen(a,b,n);
	
	serial(a,n);

	parallel(b,n);


}
